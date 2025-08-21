package main.foodie.user.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDbMapper {
  void save(User user);

  Optional<User> findByUserId(String userId);

  Optional<User> findByNickname(String nickname);

  List<User> findAll();

  void clear();
}
