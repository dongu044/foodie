package main.foodie.domain.user.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDbMapper {
  void save(User user);

  Optional<User> findByUserId(String userId);

  Optional<String> findByNickname(String nickname);

  List<User> findAll();

  void clear();
}
