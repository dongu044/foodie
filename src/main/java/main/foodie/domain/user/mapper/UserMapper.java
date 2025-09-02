package main.foodie.domain.user.mapper;

import java.util.List;
import main.foodie.domain.user.domain.User;
import main.foodie.domain.user.dto.UserApiDto;
import main.foodie.domain.user.dto.UserSignUpDto;
import main.foodie.domain.user.dto.UserValidationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", constant = "USER")
  User signUpDtoToDomain(UserSignUpDto userDto);

  UserApiDto toApiDto(User user);

  List<UserApiDto> toApiDtoList(List<User> Users);
}
