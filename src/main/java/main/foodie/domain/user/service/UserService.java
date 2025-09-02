package main.foodie.domain.user.service;

import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.dto.UserLoginDto;
import main.foodie.domain.user.dto.UserResponseDto;

public interface UserService {

  void signUp(UserSignUpDto user);

  UserResponseDto login(UserLoginDto user);
}
