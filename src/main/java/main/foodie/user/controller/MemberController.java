package main.foodie.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.foodie.user.dto.UserSignUpDto;
import main.foodie.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MemberController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<SuccessResponse> signUpForm(@Validated @RequestBody UserSignUpDto user) {
    userService.signUp(user);
    return ResponseEntity.ok(new SuccessResponse("SIGNUP_SUCCESS","회원가입 성공"));
  }

  record SuccessResponse(String code, String message) { }
}
