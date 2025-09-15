package main.foodie.service;

import java.util.Optional;
import main.foodie.domain.user.User;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import main.foodie.dto.user.UserLoginDTO;

public interface UserService {

  Long signUp(UserSignUpDto user);

  UserApiDto login(UserLoginDTO user);

  User getValidUserById(Long id);

  Optional<User> getUser(Long id);

  Optional<User> getUser(String userId);

  void deleteUser(UserApiDto dto, String password);
}
