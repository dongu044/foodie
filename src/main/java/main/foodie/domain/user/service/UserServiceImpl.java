package main.foodie.domain.user.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.domain.UserErrorCode;
import main.foodie.domain.user.domain.Role;
import main.foodie.domain.user.domain.User;
import main.foodie.domain.user.dto.UserValidationDto;
import main.foodie.domain.user.dto.UserApiDto;
import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.mapper.UserDbMapper;
import main.foodie.domain.user.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDbMapper userDbMapper;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void signUp(UserSignUpDto user) {
    validateDuplication(user);
    User newUser = userMapper.signUpDtoToDomain(user);
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    newUser.setPassword(encodedPassword);
    newUser.setRole(Role.USER);
    userDbMapper.save(newUser);
  }

  private void validateDuplication(UserSignUpDto newUser) {
    validateDuplicatedId(newUser);
    validateDuplicateNickname(newUser);
  }

  private void validateDuplicateNickname(UserSignUpDto newUser) {
    if (userDbMapper.findByNickname(newUser.getNickname()).isPresent()) {
      throw new BusinessException(UserErrorCode.NICKNAME_DUPLICATED);
    }
  }

  private void validateDuplicatedId(UserSignUpDto newUser) {
    if (userDbMapper.findByUserId(newUser.getUserId()).isPresent()) {
      throw new BusinessException(UserErrorCode.USERID_DUPLICATED);
    }
  }

  @Override
  public UserApiDto login(UserValidationDto request) {
    User userData = userDbMapper.findByUserId(request.getUserId())
        .orElseThrow(()-> new BusinessException(UserErrorCode.USERID_INVALID));

    validatePassword(request.getPassword(), userData.getPassword());

    return userMapper.toApiDto(userData);
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
