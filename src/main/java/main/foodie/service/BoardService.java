package main.foodie.service;

import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;

public interface BoardService {

  Long createPost(PostCreateRequestDTO createRequest, Long userId);

  Post getPost(Long postId);

  PostResponseDTO updatePost(PostUpdateRequestDTO updateRequest, Long postId, UserApiDto user);

  boolean deletePost(Long postId, UserApiDto user);

  CommentResponseDTO createComment(CommentRequestDTO commentRequest, Long postId, UserApiDto user);

  Comment getComment(Long commentId);

  CommentResponseDTO updateComment(CommentRequestDTO requestDTO, Long postId, Long commentId, UserApiDto user);

  boolean deleteComment(Long postId, Long commentId, UserApiDto user);
}
