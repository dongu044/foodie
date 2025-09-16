package main.foodie.mapper;

import java.util.List;
import java.util.Optional;
import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.dto.PageRequestDTO;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentUpdateDTO;
import main.foodie.dto.board.PostLikeDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

@Mapper
public interface BoardMapper {

  Long savePost(Post post);

  Optional<Post> selectPostById(Long id);

  List<Post> selectPostWithPaging(@Param("pageRequest") PageRequestDTO pageRequest);

  long countPosts(@Param("pageRequest") PageRequestDTO pageRequest);

  int insertPostLike(@Param("postId") Long postId, @Param("userId")Long userId);

  int deletePostLike(@Param("postId")Long postId, @Param("userId")Long userId);

  int incrementPostLike(@Param("postId")Long postId);

  int decrementPostLike(@Param("postId")Long postId);

  int updatePost(@Param("request") PostUpdateRequestDTO request, @Param("id") Long id);

  void softDeletePost(@Param("id") Long id);

  Long saveComment(Comment comment);

  Optional<Comment> selectCommentById(Long id);

  int updateComment(CommentUpdateDTO updateDTO);

  int insertCommentLike(@Param("commentId") Long commentId, @Param("userId")Long userId);

  int deleteCommentLike(@Param("commentId")Long commentId, @Param("userId")Long userId);

  int incrementCommentLike(@Param("commentId")Long commentId);

  int decrementCommentLike(@Param("commentId")Long commentId);

  boolean softDeleteComment(Long id);

}
