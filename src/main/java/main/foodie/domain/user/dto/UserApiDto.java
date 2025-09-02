package main.foodie.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.domain.user.domain.Role;

@Getter
@AllArgsConstructor
public class UserApiDto {

  private String userId;
  private String nickname;
  private String email;
  private Role role;
}
