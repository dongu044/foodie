package main.foodie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.common.reponse.ApiResponse;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import main.foodie.dto.user.UserValidationDto;
import main.foodie.service.UserService;
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
@Tag(name = "User API", description = "회원 관련 API")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  @Operation(summary = "회원가입", description = "신규 회원 등록")
  public ResponseEntity<ApiResponse<Long>> signup(@Validated @RequestBody UserSignUpDto user) {
    log.info("회원가입 시도: {}", user.getUserId());
    Long id = userService.signUp(user);
    return ResponseEntity.ok(new ApiResponse<>("회원가입 성공", id));
  }

  @PostMapping("/login")
  @Operation(summary = "로그인", description = "로그인 및 세션에 회원정보 등록")
  public ResponseEntity<ApiResponse<Void>> login(
      @Validated @RequestBody UserValidationDto user, HttpSession session) {
    log.info("로그인 시도: {}", user.getUserId());
    UserApiDto apiDto = userService.login(user);
    session.setAttribute("user", apiDto);
    return ResponseEntity.ok(new ApiResponse<>("로그인 성공"));
  }

  @PostMapping("/logout")
  @Operation(summary = "로그아웃", description = "로그아웃 및 세션 삭제")
  public ResponseEntity<ApiResponse<Void>> logout(HttpSession session) {
    session.invalidate();
    return ResponseEntity.ok(new ApiResponse<>("로그아웃"));

  }

  @PostMapping("/delete")
  @Operation(summary = "회원탈퇴", description = "세션 기반 회원 삭제")
  public ResponseEntity<ApiResponse<Void>> delete(@Validated @RequestBody String password, HttpSession session) {
    UserApiDto user = (UserApiDto) session.getAttribute("user");
    userService.deleteUser(user, password);
    session.invalidate();
    return ResponseEntity.ok(new ApiResponse<>("회원 탈퇴 성공"));
  }
}
