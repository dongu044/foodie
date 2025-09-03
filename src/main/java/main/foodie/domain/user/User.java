package main.foodie.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

  private Long id;
  private String userId;
  private String password;
  private String nickname;
  private String email;
  private Role role;
}
