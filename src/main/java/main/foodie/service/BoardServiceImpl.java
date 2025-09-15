package main.foodie.service;

import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.BoardErrorCode;
import main.foodie.common.exception.errorcode.CommonErrorCode;
import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.domain.user.User;
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
public class BoardServiceImpl implements BoardService{

  private final BoardMapper boardMapper;
  private final BoardMapStruct boardMapStruct;
  private final UserService userService;

  @Override
  public Long createPost(PostCreateRequestDTO request, Long userId) {
    User user = userService.getValidUserById(userId);

    Post post = boardMapStruct.toPost(request, user);

    boardMapper.savePost(post);

    return post.getId();
  }

  @Override
  public Post getPost(Long postId) {
   return boardMapper.findPostById(postId).orElseThrow(()-> new BusinessException(BoardErrorCode.POST_NOT_FOUND));
  }

  @Override
  public PostResponseDTO updatePost(PostUpdateRequestDTO request, Long postId, UserApiDto user) {
    Post post = getPostWithAuthorityCheck(postId, user);

    if (boardMapper.updatePost(request, post.getId()) == 0) {
      throw new BusinessException(CommonErrorCode.UPDATE_FAILED);
    }

    Post updatedPost = getPost(postId);

    return boardMapStruct.toPostResponseDTO(updatedPost);
  }

  @Override
  public boolean deletePost(Long postId, UserApiDto user) {
    getPostWithAuthorityCheck(postId, user);
    boardMapper.deletePost(postId);
    return true;
  }

  private Post getPostWithAuthorityCheck(Long postId, UserApiDto user) {
    Post post = getPost(postId);
    validatePostAuthor(user, post);
    return post;
  }

  private static void validatePostAuthor(UserApiDto user, Post post) {
    if (!post.getAuthorId().equals(user.getId())) {
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
    return boardMapper.findCommentById(commentId)
        .orElseThrow(() -> new BusinessException(BoardErrorCode.COMMENT_NOT_FOUND));
  }

  @Override
  public CommentResponseDTO updateComment(CommentRequestDTO request, Long postId, Long commentId,
      UserApiDto user) {
    Comment comment = getCommentWithValidation(postId, commentId, user);
    CommentUpdateDTO updateDTO = CommentUpdateDTO.of(request, commentId);
    if (boardMapper.updateComment(updateDTO) == 0) {
      throw new BusinessException(CommonErrorCode.UPDATE_FAILED);
    }
    return boardMapStruct.toCommentResponseDTO(comment);
  }

  private Comment getCommentWithValidation(Long postId, Long commentId, UserApiDto user) {
    Comment comment = getComment(commentId);
    if (!comment.getAuthorId().equals(user.getId())) {
      throw new BusinessException(BoardErrorCode.COMMENT_ACCESS_DENIED);
    }
    if (!comment.getPostId().equals(postId)) {
      throw new BusinessException(BoardErrorCode.COMMENT_ACCESS_DENIED);
    }
    return comment;
  }

  @Override
  public boolean deleteComment(Long postId, Long commentId, UserApiDto user) {
    getCommentWithValidation(postId, commentId, user);
    boardMapper.softDeleteComment(commentId);
    return true;
  }
}
