package main.foodie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.argument.LoginUser;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping("/posts")
  public ResponseEntity<ApiResponse<Long>> createPost(
      @Validated @RequestBody PostCreateRequestDTO request, @LoginUser UserApiDto user) {
    Long id = boardService.createPost(request, user.getId());
    return ResponseEntity.ok(new ApiResponse<>("신규 게시글 등록", id));
  }

  @PutMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(
      @Validated @RequestBody PostUpdateRequestDTO request, @PathVariable Long id,
      @LoginUser UserApiDto user) {
    PostResponseDTO postResponseDTO = boardService.updatePost(request, id, user);
    return ResponseEntity.ok(new ApiResponse<>("게시글 수정완료", postResponseDTO));
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<Boolean>> deletePost(@PathVariable Long id, UserApiDto user) {
    boolean result = boardService.deletePost(id, user);
    return ResponseEntity.ok(new ApiResponse<>("게시글 삭제완료", result));
  }

  @PostMapping("/posts/{postId}/comments")
  public ResponseEntity<ApiResponse<CommentResponseDTO>> createComment(
      @Validated @RequestBody CommentRequestDTO request, @PathVariable Long postId,
      @LoginUser UserApiDto user) {
    CommentResponseDTO response = boardService.createComment(request, postId, user);
    return ResponseEntity.ok(new ApiResponse<>("댓글 등록완료", response));
  }

  @PutMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<ApiResponse<CommentResponseDTO>> updateComment(
      @Validated @RequestBody CommentRequestDTO request, @PathVariable Long postId, @PathVariable Long commentId,
      @LoginUser UserApiDto user) {
    CommentResponseDTO response = boardService.updateComment(request, postId, commentId, user);
    return ResponseEntity.ok(new ApiResponse<>("댓글 수정완료", response));
  }

  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<ApiResponse<Boolean>> deleteComment(
      @PathVariable Long postId, @PathVariable Long commentId,
      @LoginUser UserApiDto user) {
    Boolean result = boardService.deleteComment(postId, commentId, user);
    return ResponseEntity.ok(new ApiResponse<>("댓글 삭제완료", result));
  }
}
