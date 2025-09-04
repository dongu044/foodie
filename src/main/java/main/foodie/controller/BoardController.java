package main.foodie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.argument.LoginUser;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<ApiResponse<Long>> createPost(@Validated @RequestBody PostCreateRequestDTO dto, @LoginUser UserApiDto userApiDto) {
    Long id = boardService.createPost(dto, userApiDto.getUserId());
    return ResponseEntity.ok(new ApiResponse<>("신규 게시글 등록", id));
  }

  @PostMapping("/posts/update/{id}")
  public ResponseEntity<ApiResponse<PostResponseDTO>> updatePost(@Validated @RequestBody PostUpdateRequestDTO request, @PathVariable Long id) {
    PostResponseDTO postResponseDTO = boardService.updatePost(request, id);
    return ResponseEntity.ok(new ApiResponse<>("게시글 수정완료", postResponseDTO));
  }
}
