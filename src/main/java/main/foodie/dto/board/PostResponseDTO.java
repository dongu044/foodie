package main.foodie.dto.board;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.domain.board.Visibility;

@Getter
@AllArgsConstructor
public class PostResponseDTO {

  private String nickname;

  private String category;
  private String title;
  private Visibility visibility;
  private String content;

  private int viewCount;
  private int likeCount;
  private int commentCount;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public String getVisibility() {
    return visibility.name();
  }
}
