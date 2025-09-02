package main.foodie.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpDto {

  @NotBlank(message="아이디는 필수 입력값입니다.")
  @Pattern(
      regexp = "^[A-Za-z][A-Za-z0-9]{6,15}$",
      message = "아이디는 영문 또는 영어와 숫자를 혼합하여 6자 이상, 15자 이하이어야 합니다."
  )
  private String userId;

  @NotBlank(message="비밀번호는 필수 입력값입니다.")
  @Pattern(
      regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{10,20}$",
      message = "비밀번호는 영문, 숫자, 특수문자를 모두 포함하여 10자 이상, 20자 이하이어야 합니다."
  )
  private String password;

  @NotBlank(message="별명은 필수 입력값입니다.")
  @Pattern(
      regexp = "^[A-Za-z가-힣0-9]{1,15}$",
      message = "별명은 영문,한글,숫자를 혼합하여 1자 이상, 15자 이하이어야 합니다."
  )
  private String nickname;

  @Email(message = "올바른 이메일 형식이 아닙니다.")
  private String email;
}
