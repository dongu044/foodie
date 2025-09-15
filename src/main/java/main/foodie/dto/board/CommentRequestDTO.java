package main.foodie.dto.board;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.foodie.domain.board.Visibility;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

  @Nullable
  private Long parentId;

  @NotBlank
  private String content;

  @NotNull
  private Visibility visibility;
}
