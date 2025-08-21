package main.foodie.user.service;

import main.foodie.user.dto.UserSignUpDto;

public interface UserService {

  void signUp(UserSignUpDto user);

  void login(String userId);
}
