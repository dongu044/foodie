package main.foodie.service;

import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.domain.UserErrorCode;
import main.foodie.domain.user.Role;
import main.foodie.domain.user.User;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import main.foodie.dto.user.UserValidationDto;
import main.foodie.mapper.UserDbMapper;
import main.foodie.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDbMapper userDbMapper;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Long signUp(UserSignUpDto user) {
    isDuplicated(user);
    User newUser = userMapper.signUpDtoToDomain(user);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    newUser.setPassword(encodedPassword);
    newUser.setRole(Role.USER);
    return userDbMapper.save(newUser);
  }

  private void isDuplicated(UserSignUpDto newUser) {
    isDuplicatedUserId(newUser);
    isDuplicatedNickname(newUser);
  }

  private void isDuplicatedNickname(UserSignUpDto newUser) {
    if (userDbMapper.findByNickname(newUser.getNickname()).isPresent()) {
      throw new BusinessException(UserErrorCode.NICKNAME_DUPLICATED);
    }
  }

  private void isDuplicatedUserId(UserSignUpDto newUser) {
    if (userDbMapper.findByUserId(newUser.getUserId()).isPresent()) {
      throw new BusinessException(UserErrorCode.USERID_DUPLICATED);
    }
  }

  @Override
  public UserApiDto login(UserValidationDto request) {
    User userData = isValidUserId(request.getUserId());

    validatePassword(request.getPassword(), userData.getPassword());

    return userMapper.toApiDto(userData);
  }

  public User isValidUserId(String userId) {
    return userDbMapper.findByUserId(userId)
        .orElseThrow(() -> new BusinessException(UserErrorCode.USERID_INVALID));
  }

  @Override
  public void deleteUser(UserApiDto request, String password) {
    User userData = userDbMapper.findByUserId(request.getUserId())
        .orElseThrow(()-> new BusinessException(UserErrorCode.USERID_INVALID));

    validatePassword(password, userData.getPassword());

    userDbMapper.deleteUser(userData.getUserId());
  }

  private void validatePassword(String rawPassword, String encodedPassword) {
    if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
      throw new BusinessException(UserErrorCode.PASSWORD_INVALID);
    }
  }
}
