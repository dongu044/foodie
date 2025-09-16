package main.foodie.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.argument.LoginUser;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.domain.board.Post;
import main.foodie.dto.PageRequestDTO;
import main.foodie.dto.PageResponseDTO;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.service.BoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/posts")
  public ResponseEntity<ApiResponse<PageResponseDTO<Post>>> getPosts(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "created_at") String sortBy,
      @RequestParam(defaultValue = "DESC") String sortDir,
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category
  ) {
    PageRequestDTO pageRequest = PageRequestDTO.builder()
        .page(page)
        .size(size)
        .sortBy(sortBy)
        .sortDir(sortDir)
        .keyword(keyword)
        .category(category)
        .build();

    PageResponseDTO<Post> response = boardService.getPostList(pageRequest);
    return ResponseEntity.ok(new ApiResponse<>("게시글 목록 조회 완료", response));
  }

  @GetMapping("/posts/{type}")
  public ResponseEntity<ApiResponse<PageResponseDTO<Post>>> getPostsByType(
      @PathVariable String type,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category
  ) {
    PageRequestDTO pageRequest = PageRequestDTO.of(type, page, size, keyword, category);

    PageResponseDTO<Post> response = boardService.getPostList(pageRequest);
    return ResponseEntity.ok(new ApiResponse<>(type + "게시글 조회 완료", response));
  }

  @PostMapping("/posts")
  public ResponseEntity<ApiResponse<Long>> createPost(
      @Validated @RequestBody PostCreateRequestDTO request, @LoginUser UserApiDto user) {
    Long id = boardService.createPost(request, user);
    return ResponseEntity.ok(new ApiResponse<>("신규 게시글 등록", id));
  }

  @PutMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(
      @Validated @RequestBody PostUpdateRequestDTO request, @PathVariable Long id,
      @LoginUser UserApiDto user) {
    PostResponseDTO postResponseDTO = boardService.updatePost(request, id, user.getId());
    return ResponseEntity.ok(new ApiResponse<>("게시글 수정완료", postResponseDTO));
  }

  @PostMapping("/posts/{id}/like")
  public ResponseEntity<ApiResponse<Map<String, Object>>> togglePostLike(
      @PathVariable Long id, @LoginUser UserApiDto user) {
    boolean isLiked = boardService.togglePostLike(id, user.getId());
    Map<String, Object> result = getToggleLikeMap(isLiked);
    return ResponseEntity.ok(new ApiResponse<>("좋아요 처리완료", result));
  }

  @DeleteMapping("/posts/{id}")
  public ResponseEntity<ApiResponse<Boolean>> deletePost(@PathVariable Long id, UserApiDto user) {
    boolean result = boardService.deletePost(id, user.getId());
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
      @Validated @RequestBody CommentRequestDTO request, @PathVariable Long postId,
      @PathVariable Long commentId,
      @LoginUser UserApiDto user) {
    CommentResponseDTO response = boardService.updateComment(request, postId, commentId,
        user.getId());
    return ResponseEntity.ok(new ApiResponse<>("댓글 수정완료", response));
  }

  @PostMapping("/posts/{postId}/comments/{commentId}/like")
  public ResponseEntity<ApiResponse<Map<String, Object>>> toggleCommentLike(
      @PathVariable Long postId, @PathVariable Long commentId, @LoginUser UserApiDto user) {
    boolean isLiked = boardService.toggleCommentLike(postId, commentId, user.getId());
    return ResponseEntity.ok(new ApiResponse<>("좋아요 처리완료", getToggleLikeMap(isLiked)));
  }

  private static Map<String, Object> getToggleLikeMap(boolean isLiked) {
    Map<String, Object> result = new HashMap<>();
    result.put("isLiked", isLiked);
    result.put("message", isLiked ? "좋아요" : "좋아요 취소");
    return result;
  }

  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<ApiResponse<Boolean>> deleteComment(
      @PathVariable Long postId, @PathVariable Long commentId,
      @LoginUser UserApiDto user) {
    Boolean result = boardService.deleteComment(postId, commentId, user.getId());
    return ResponseEntity.ok(new ApiResponse<>("댓글 삭제완료", result));
  }
}
