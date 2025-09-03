package main.foodie.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDbMapper {
  Long save(User user);

  Optional<User> findByUserId(String userId);

  Optional<String> findByNickname(String nickname);

  void deleteUser(String userId);

  List<User> findAll();

  void clear();
}
