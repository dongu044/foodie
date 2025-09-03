package main.foodie.dto.board;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.foodie.domain.board.Visibility;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

  @NotBlank
  private Long postId;

  @Nullable
  private Long parentId;

  @NotBlank
  private String content;

  @NotBlank
  private Visibility visibility;
}
