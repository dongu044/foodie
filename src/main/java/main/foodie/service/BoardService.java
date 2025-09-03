package main.foodie.service;

import main.foodie.dto.board.CommentDTO;
import main.foodie.dto.board.CreatePostDTO;
import main.foodie.dto.board.UpdatePostDTO;

public interface BoardService {

  void createPost(CreatePostDTO createRequest, String userId);

  UpdatePostDTO updatePost(Long id);

  void deletePost(Long id);

  void comment(CommentDTO commentRequest, String userId);

  CommentDTO updateComment(Long id);

  void deleteComment(Long id);
}
