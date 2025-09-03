package main.foodie.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.domain.user.Role;

@Getter
@AllArgsConstructor
public class UserApiDto {

  private String userId;
  private String nickname;
  private String email;
  private Role role;
}
