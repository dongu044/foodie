package main.foodie.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.BoardErrorCode;
import main.foodie.common.exception.errorcode.CommonErrorCode;
import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.dto.PageRequestDTO;
import main.foodie.dto.PageResponseDTO;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.CommentUpdateDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.mapper.BoardMapper;
import main.foodie.mapper.BoardMapStruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

  private final BoardMapper boardMapper;
  private final BoardMapStruct boardMapStruct;
  private final UserService userService;

  @Override
  public Long createPost(PostCreateRequestDTO request, UserApiDto user) {
    Post post = boardMapStruct.toPost(request, user);

    boardMapper.savePost(post);

    return post.getId();
  }

  @Override
  public Post getPost(Long postId) {
   return boardMapper.selectPostById(postId).orElseThrow(()-> new BusinessException(BoardErrorCode.POST_NOT_FOUND));
  }

  @Override
  @Transactional(readOnly = true)
  public PageResponseDTO<Post> getPostList(PageRequestDTO pageRequest) {
    log.info("게시글 목록 조회 - 페이지: {}, 크기: {}, 정렬: {} {}",
        pageRequest.getPage(), pageRequest.getSize(),
        pageRequest.getSortBy(), pageRequest.getSortDir());
    if (pageRequest.getSize() > 100) {
      pageRequest.setSize(100);
    }
    if (pageRequest.getPage() < 1) {
      pageRequest.setSize(1);
    }

    List<Post> posts = boardMapper.selectPostWithPaging(pageRequest);
    long totalElements = boardMapper.countPosts(pageRequest);
    log.info("조회 완료 - 총 {}건 중 {}개 조회", totalElements, posts.size());
    return PageResponseDTO.of(posts,totalElements,pageRequest);
  }

  @Override
  public PostResponseDTO updatePost(PostUpdateRequestDTO request, Long postId, Long userId) {
    Post post = getPostWithAuthorityCheck(postId, userId);

    if (boardMapper.updatePost(request, post.getId()) == 0) {
      throw new BusinessException(CommonErrorCode.UPDATE_FAILED);
    }

    Post updatedPost = getPost(postId);

    return boardMapStruct.toPostResponseDTO(updatedPost);
  }

  @Override
  public boolean togglePostLike(Long postId, Long userId) {
    getPost(postId);

    int deletedRows = boardMapper.deletePostLike(postId, userId);

    if (deletedRows > 0) {
      boardMapper.decrementPostLike(postId);
      return false;
    } else {
      boardMapper.insertPostLike(postId, userId);
      boardMapper.incrementPostLike(postId);
      return true;
    }
  }

  @Override
  public boolean deletePost(Long postId, Long userId) {
    getPostWithAuthorityCheck(postId, userId);
    boardMapper.softDeletePost(postId);
    return true;
  }

  private Post getPostWithAuthorityCheck(Long postId, Long userId) {
    Post post = getPost(postId);
    validatePostAuthor(userId, post);
    return post;
  }

  private static void validatePostAuthor(Long userId, Post post) {
    if (!post.getAuthorId().equals(userId)) {
      throw new BusinessException(BoardErrorCode.POST_ACCESS_DENIED);
    }
  }

  // Comment 비즈니스 로직
  @Override
  public CommentResponseDTO createComment(CommentRequestDTO request, Long postId, UserApiDto user) {
    Post post = getPost(postId);
    validateCommentCreationRequest(request, postId);
    Comment comment = boardMapStruct.toComment(request, post, user);
    boardMapper.saveComment(comment);
    return boardMapStruct.toCommentResponseDTO(comment);
  }

  private void validateCommentCreationRequest(CommentRequestDTO request, Long postId) {
    // 부모댓글이 존재하는지
    if (request.getParentId() != null) {
      Comment parent = getComment(request.getParentId());
      // 부모댓글이 같은 게시글에 속하는지
      if (!parent.getPostId().equals(postId)) {
        throw new BusinessException(BoardErrorCode.COMMENT_ACCESS_DENIED);
      }
      // 대댓글에 답글 방지
      if (parent.getParentId() != null) {
        throw new BusinessException(BoardErrorCode.REPLY_DEPTH_EXCEED);
      }
    }
  }

  public Comment getComment(Long commentId) {
    return boardMapper.selectCommentById(commentId)
        .orElseThrow(() -> new BusinessException(BoardErrorCode.COMMENT_NOT_FOUND));
  }

  @Override
  public CommentResponseDTO updateComment(CommentRequestDTO request, Long postId, Long commentId,
      Long userId) {
    Comment comment = getCommentWithAuthorityCheck(postId, commentId, userId);
    CommentUpdateDTO updateDTO = CommentUpdateDTO.of(request, commentId);
    if (boardMapper.updateComment(updateDTO) == 0) {
      throw new BusinessException(CommonErrorCode.UPDATE_FAILED);
    }
    return boardMapStruct.toCommentResponseDTO(comment);
  }

  @Override
  public boolean toggleCommentLike(Long postId, Long commentId, Long userId) {
    getPost(postId);
    getComment(commentId);
    int deletedRows = boardMapper.deleteCommentLike(commentId, userId);
    if (deletedRows > 0) {
      boardMapper.decrementCommentLike(commentId);
      return false;
    } else {
      boardMapper.insertCommentLike(commentId, userId);
      boardMapper.incrementCommentLike(commentId);
      return true;
    }
  }


  private Comment getCommentWithAuthorityCheck(Long postId, Long commentId, Long userId) {
    Comment comment = getComment(commentId);
    if (!comment.getAuthorId().equals(userId)) {
      throw new BusinessException(BoardErrorCode.COMMENT_ACCESS_DENIED);
    }
    if (!comment.getPostId().equals(postId)) {
      throw new BusinessException(BoardErrorCode.POST_ACCESS_DENIED);
    }
    return comment;
  }

  @Override
  public boolean deleteComment(Long postId, Long commentId, Long userId) {
    getCommentWithAuthorityCheck(postId, commentId, userId);
    boardMapper.softDeleteComment(commentId);
    return true;
  }
}
