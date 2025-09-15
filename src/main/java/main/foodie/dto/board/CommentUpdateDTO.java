package main.foodie.dto.board;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.foodie.domain.board.Visibility;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDTO {
  private Long id;
  private Long parentId;
  private String content;
  private Visibility visibility;

  public static CommentUpdateDTO of(CommentRequestDTO request, Long commentId) {
    return new CommentUpdateDTO(commentId, request.getParentId(),
        request.getContent(), request.getVisibility());
  }
}
