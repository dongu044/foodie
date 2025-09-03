package main.foodie.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.domain.board.Visibility;

@Getter
@AllArgsConstructor
public class CreatePostDTO {

  @NotBlank(message = "말머리 선택은 필수입니다")
  private String category;

  @NotBlank(message = "글 제목은 필수입니다")
  @Size(max=50)
  private String title;

  @NotBlank
  private Visibility visibility;

  @NotBlank
  @Size(max=3000)
  private String content;
}
