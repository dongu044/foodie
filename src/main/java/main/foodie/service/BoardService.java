package main.foodie.service;

import main.foodie.domain.board.Post;
import main.foodie.dto.board.CommentDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;

public interface BoardService {

  Long createPost(PostCreateRequestDTO createRequest, String userId);

  PostResponseDTO updatePost(PostUpdateRequestDTO updateRequest);

  void deletePost(Long id);

  void comment(CommentDTO commentRequest, String userId);

  CommentDTO updateComment(Long id);

  void deleteComment(Long id);
}
