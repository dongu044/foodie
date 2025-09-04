package main.foodie.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.PostUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDbMapper {

  Long createPost(Post post);

  Optional<Post> findPostById(Long id);

  List<Post> findPostByNickname(String nickname);

  List<Post> findPostByTitle(String keyword);

  List<Post> findPostByTitleAndContent(String keyword);

  List<Post> findPostByTitleAndContentAndNickname(String keyword);

  int updatePost(PostUpdateRequestDTO request);

  void deletePost(Long id);

  void clear();
}
