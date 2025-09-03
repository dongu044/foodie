package main.foodie.service;

import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.domain.BoardErrorCode;
import main.foodie.domain.board.Post;
import main.foodie.domain.user.User;
import main.foodie.dto.board.CommentDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.mapper.BoardDbMapper;
import main.foodie.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

  private final BoardDbMapper boardDbMapper;
  private final BoardMapper boardMapper;
  private final UserService userService;

  @Override
  public Long createPost(PostCreateRequestDTO createRequest, String userId) {
    User user = userService.isValidUserId(userId);

    Post post = boardMapper.toPost(createRequest, user);

    return boardDbMapper.createPost(post);
  }

  @Override
  @Transactional
  public PostResponseDTO updatePost(PostUpdateRequestDTO request) {
    Post post = getPost(request);

    int affectedRows = boardDbMapper.updatePost(request);
    if (affectedRows == 0) {
      throw new BusinessException(BoardErrorCode.POST_UPDATE_FAILED);
    }

    boardMapper.updatePostFromRequest(request, post);
    return boardMapper.toPostResponseDTO(post);
  }

  private Post getPost(PostUpdateRequestDTO request) {
    return boardDbMapper.findPostById(request.getId())
        .orElseThrow(() -> new BusinessException(BoardErrorCode.POST_NOT_FOUND));
  }

  @Override
  public void deletePost(Long id) {

  }

  @Override
  public void comment(CommentDTO commentRequest, String userId) {

  }

  @Override
  public CommentDTO updateComment(Long id) {
    return null;
  }

  @Override
  public void deleteComment(Long id) {

  }
}
