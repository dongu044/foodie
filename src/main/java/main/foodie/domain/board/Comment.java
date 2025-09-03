package main.foodie.domain.board;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long id;
  private Long postId;
  private Long parentId;
  private Long authorId;
  private String nickname;

  private String content;
  private Visibility visibility;
  private int likeCount;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

}
