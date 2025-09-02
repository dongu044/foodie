package main.foodie.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserValidationDto {

  private String userId;
  private String password;
}
