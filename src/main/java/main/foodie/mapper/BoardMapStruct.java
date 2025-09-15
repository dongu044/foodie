package main.foodie.mapper;

import main.foodie.domain.board.Comment;
import main.foodie.domain.board.Post;
import main.foodie.domain.user.User;
import main.foodie.dto.board.CommentRequestDTO;
import main.foodie.dto.board.CommentResponseDTO;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import main.foodie.dto.user.UserApiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BoardMapStruct {

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "user.id", target = "authorId")
  @Mapping(source = "user.nickname", target = "nickname")
  @Mapping(target = "visibility")
  @Mapping(target = "viewCount", ignore = true)
  @Mapping(target = "likeCount", ignore = true)
  @Mapping(target = "commentCount", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  Post toPost(PostCreateRequestDTO dto, User user);

  PostResponseDTO toPostResponseDTO(Post post);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "authorId", ignore = true)
  @Mapping(target = "viewCount", ignore = true)
  @Mapping(target = "likeCount", ignore = true)
  @Mapping(target = "commentCount", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  Post requestToPost(PostUpdateRequestDTO request, @MappingTarget Post post);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "post.id", target = "postId")
  @Mapping(source = "request.parentId", target= "parentId")
  @Mapping(source = "user.id", target = "authorId")
  @Mapping(source = "user.nickname", target = "nickname")
  @Mapping(source = "request.visibility", target = "visibility")
  @Mapping(source = "request.content", target = "content")
  @Mapping(target = "likeCount", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "deleted", ignore = true)
  Comment toComment(CommentRequestDTO request, Post post, UserApiDto user);

  CommentResponseDTO toCommentResponseDTO(Comment comment);
}
