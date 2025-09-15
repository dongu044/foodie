package main.foodie.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentUpdateDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {

  Long savePost(Post post);

  Optional<Post> findPostById(Long id);

  List<Post> findPostByNickname(String nickname);

  List<Post> findPostByTitle(String keyword);

  List<Post> findPostByTitleAndContent(String keyword);

  List<Post> findPostByTitleAndContentAndNickname(String keyword);

  int updatePost(@Param("request") PostUpdateRequestDTO request, @Param("id") Long id);

  void deletePost(@Param("id") Long id);

  Long saveComment(Comment comment);

  Optional<Comment> findCommentById(Long id);

  int updateComment(CommentUpdateDTO updateDTO);

  boolean softDeleteComment(Long id);
}
