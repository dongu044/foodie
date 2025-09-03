package main.foodie.mapper;

import main.foodie.domain.board.Post;
import main.foodie.domain.user.User;
import main.foodie.dto.board.PostCreateRequestDTO;
import main.foodie.dto.board.PostResponseDTO;
import main.foodie.dto.board.PostUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BoardMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "user.id", target = "authorId")
  @Mapping(source = "user.nickname", target = "nickname")
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
  void updatePostFromRequest(PostUpdateRequestDTO request, @MappingTarget Post post);
}
