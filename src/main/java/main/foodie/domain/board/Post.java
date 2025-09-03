package main.foodie.domain.board;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  private Long id;
  private Long authorId;
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
  private LocalDateTime deletedAt;

  public String getVisibility() {
    return visibility.name();
  }
}
