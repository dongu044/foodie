package main.foodie.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.domain.user.dto.UserApiDto;
import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.service.UserService;
import main.foodie.domain.user.dto.UserValidationDto;
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
  public ResponseEntity<ApiResponse<Void>> login(
      @Validated @RequestBody UserValidationDto user, HttpSession session) {
    log.info("로그인 시도: {}", user.getUserId());
    UserApiDto apiDto = userService.login(user);
    session.setAttribute("user", apiDto);
    return ResponseEntity.ok(new ApiResponse<>("로그인 성공"));
  }

  @PostMapping("/delete")
  public ResponseEntity<ApiResponse<Void>> delete(@Validated @RequestBody String password, HttpSession session) {
    UserApiDto user = (UserApiDto) session.getAttribute("user");
    userService.deleteUser(user, password);
    session.invalidate();
    return ResponseEntity.ok(new ApiResponse<>("회원 탈퇴 성공"));
  }
}
