package main.foodie.service;

import main.foodie.domain.user.User;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import main.foodie.dto.user.UserValidationDto;

public interface UserService {

  Long signUp(UserSignUpDto user);

  UserApiDto login(UserValidationDto user);

  void deleteUser(UserApiDto dto, String password);

  User isValidUserId(String userId);
}
