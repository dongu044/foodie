package main.foodie.user.mapper;

import java.util.List;
import main.foodie.user.domain.User;
import main.foodie.user.dto.UserLoginDto;
import main.foodie.user.dto.UserResponseDto;
import main.foodie.user.dto.UserSignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", constant = "USER")
  User signUpDtoToDomain(UserSignUpDto userDto);

  UserResponseDto toResponseDto(User user);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "nickname", ignore = true)
  @Mapping(target = "email", ignore = true)
  @Mapping(target = "role", ignore = true)
  User loginDtoToDomain(UserLoginDto loginDto);

  List<UserResponseDto> toResponseDtoList(List<User> Users);
}
