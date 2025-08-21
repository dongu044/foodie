package main.foodie.user.service;

import lombok.RequiredArgsConstructor;
import main.foodie.exception.NicknameDuplicatedException;
import main.foodie.exception.UserIdDuplicatedException;
import main.foodie.user.domain.Role;
import main.foodie.user.domain.User;
import main.foodie.user.dto.UserSignUpDto;
import main.foodie.user.mapper.UserDbMapper;
import main.foodie.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDbMapper userDbMapper;
  private final UserMapper userMapper;

  @Override
  public void signUp(UserSignUpDto user) {
    validateDuplication(user);
    User newUser = userMapper.signUpDtoToDomain(user);
    newUser.setRole(Role.USER);
    userDbMapper.save(newUser);
  }

  private void validateDuplication(UserSignUpDto newUser) {
    validateDuplicatedId(newUser);
    validateDuplicateNickname(newUser);
  }

  private void validateDuplicateNickname(UserSignUpDto newUser) {
    if (userDbMapper.findByNickname(newUser.getNickname()).isPresent()) {
      throw new NicknameDuplicatedException();
    }
  }

  private void validateDuplicatedId(UserSignUpDto newUser) {
    if (userDbMapper.findByUserId(newUser.getUserId()).isPresent()) {
      throw new UserIdDuplicatedException();
    }
  }

  @Override
  public void login(String userId) {

  }
}
