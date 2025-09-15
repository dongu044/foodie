package main.foodie.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.UserErrorCode;
import main.foodie.domain.user.Role;
import main.foodie.domain.user.User;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import main.foodie.dto.user.UserLoginDTO;
import main.foodie.mapper.UserMapper;
import main.foodie.mapper.UserMapStruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserMapStruct userMapStruct;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Long signUp(UserSignUpDto user) {
    isDuplicated(user);
    User newUser = userMapStruct.signUpDtoToDomain(user);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    newUser.setPassword(encodedPassword);
    newUser.setRole(Role.USER);
    userMapper.save(newUser);
    return newUser.getId();
  }

  private void isDuplicated(UserSignUpDto newUser) {
    isDuplicatedUserId(newUser);
    isDuplicatedNickname(newUser);
  }

  private void isDuplicatedNickname(UserSignUpDto newUser) {
    if (userMapper.findByNickname(newUser.getNickname()).isPresent()) {
      throw new BusinessException(UserErrorCode.NICKNAME_DUPLICATED);
    }
  }

  private void isDuplicatedUserId(UserSignUpDto newUser) {
    if (userMapper.findByUserId(newUser.getUserId()).isPresent()) {
      throw new BusinessException(UserErrorCode.USERID_DUPLICATED);
    }
  }

  @Override
  public UserApiDto login(UserLoginDTO request) {
    User user = getValidUser(request);

    validatePassword(request.getPassword(), user.getPassword());

    return userMapStruct.toApiDto(user);
  }

  @Override
  public User getValidUserById(Long id) {
    return getUser(id).orElseThrow(
        () -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
  }

  public User getValidUser(UserLoginDTO request) {
    return getUser(request.getUserId()).orElseThrow(
        () -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
  }

  @Override
  public Optional<User> getUser(Long id) {
    return userMapper.findById(id);
  }

  @Override
  public Optional<User> getUser(String userId) {
    return userMapper.findByUserId(userId);
  }

  @Override
  public void deleteUser(UserApiDto request, String password) {
    User userData = userMapper.findByUserId(request.getUserId())
        .orElseThrow(()-> new BusinessException(UserErrorCode.USERID_INVALID));

    validatePassword(password, userData.getPassword());

    userMapper.deleteUser(userData.getUserId());
  }

  private void validatePassword(String rawPassword, String encodedPassword) {
    if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
      throw new BusinessException(UserErrorCode.PASSWORD_INVALID);
    }
  }
}
