package main.foodie.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.board.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDbMapper {

  void createPost(Post post);

  Optional<Post> findPostById(Long id);

  List<Post> findPostByAuthor(String nickname);

  List<Post> findPostByTitle(String keyword);

  List<Post> findPostByTitleAndContent(String keyword);

  void deletePost(Long id);

  void clear();
}
