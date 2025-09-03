package main.foodie.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserValidationDto {

  private String userId;
  private String password;
}
