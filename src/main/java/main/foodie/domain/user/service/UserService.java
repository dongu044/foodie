package main.foodie.domain.user.service;

import jakarta.servlet.http.HttpSession;
import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.dto.UserValidationDto;
import main.foodie.domain.user.dto.UserApiDto;

public interface UserService {

  void signUp(UserSignUpDto user);

  UserApiDto login(UserValidationDto user);

  void deleteUser(UserApiDto dto, String password);
}
