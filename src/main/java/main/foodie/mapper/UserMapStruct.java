package main.foodie.mapper;

import java.util.List;
import main.foodie.domain.user.User;
import main.foodie.dto.user.UserApiDto;
import main.foodie.dto.user.UserSignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "role", constant = "USER")
  User signUpDtoToDomain(UserSignUpDto userDto);

  UserApiDto toApiDto(User user);

  List<UserApiDto> toApiDtoList(List<User> Users);
}
