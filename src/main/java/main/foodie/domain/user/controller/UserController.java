package main.foodie.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.domain.user.dto.UserResponseDto;
import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.service.UserService;
import main.foodie.domain.user.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<Void>> signup(@Validated @RequestBody UserSignUpDto user) {
    log.info("회원가입 시도: {}", user.getUserId());
    userService.signUp(user);
    return ResponseEntity.ok(new ApiResponse<>("회원가입 성공"));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<UserResponseDto>> login(@Validated @RequestBody UserLoginDto user) {
    log.info("로그인 시도: {}", user.getUserId());
    UserResponseDto responseDto = userService.login(user);
    return ResponseEntity.ok(new ApiResponse<>("로그인 성공", responseDto));
  }
}
