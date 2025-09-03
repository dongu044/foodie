package main.foodie.service;

import lombok.RequiredArgsConstructor;
import main.foodie.domain.board.Post;
import main.foodie.domain.user.User;
import main.foodie.dto.board.CommentDTO;
import main.foodie.dto.board.CreatePostDTO;
import main.foodie.dto.board.UpdatePostDTO;
import main.foodie.mapper.BoardDbMapper;
import main.foodie.mapper.BoardMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

  private final BoardDbMapper boardDbMapper;
  private final BoardMapper boardMapper;
  private final UserService userService;

  @Override
  public void createPost(CreatePostDTO createRequest, String userId) {
    User user = userService.isValidUserId(userId);

    Post post = boardMapper.toPost(createRequest, user);

    boardDbMapper.createPost(post);
  }

  @Override
  public UpdatePostDTO updatePost(Long id) {
    return null;
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
