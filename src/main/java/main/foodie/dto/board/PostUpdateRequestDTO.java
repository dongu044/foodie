package main.foodie.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import main.foodie.domain.board.Visibility;

@Data
@AllArgsConstructor
public class PostUpdateRequestDTO {

  @NotNull
  private Long id;

  @NotBlank
  private String nickname;

  @NotBlank
  private String category;

  @Size(max=50)
  private String title;

  @NotNull
  private Visibility visibility;

  @NotBlank
  @Size(max=3000)
  private String content;
}
