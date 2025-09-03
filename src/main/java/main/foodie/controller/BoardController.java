package main.foodie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.argument.LoginUser;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.dto.board.CreatePostDTO;
import main.foodie.dto.user.UserApiDto;
import main.foodie.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

  @PostMapping("/post")
  public ResponseEntity<ApiResponse<Void>> post(@Validated @RequestBody CreatePostDTO dto, @LoginUser UserApiDto userApiDto) {
    boardService.createPost(dto, userApiDto.getUserId());
    return ResponseEntity.ok(new ApiResponse<>("신규 게시글 등록"));
  }
}
