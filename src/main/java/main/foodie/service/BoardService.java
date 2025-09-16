package main.foodie.service;

import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostLikeDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;

public interface BoardService {

  Long createPost(PostCreateRequestDTO request, UserApiDto user);

  Post getPost(Long postId);

//  List<Post> getPostList();

  PostResponseDTO updatePost(PostUpdateRequestDTO updateRequest, Long postId, Long userId);

  boolean togglePostLike(Long postId, Long userId);

  boolean deletePost(Long postId, Long userId);

  CommentResponseDTO createComment(CommentRequestDTO commentRequest, Long postId, UserApiDto user);

  Comment getComment(Long commentId);

  CommentResponseDTO updateComment(CommentRequestDTO requestDTO, Long postId, Long commentId, Long userId);

  boolean toggleCommentLike(Long postId, Long commentId, Long userId);

  boolean deleteComment(Long postId, Long commentId, Long userId);
}
