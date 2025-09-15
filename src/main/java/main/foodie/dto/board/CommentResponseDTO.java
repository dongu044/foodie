package main.foodie.dto.board;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import main.foodie.domain.board.Visibility;
import org.springframework.cglib.core.Local;

@Getter
@AllArgsConstructor
public class CommentResponseDTO {

  private Long id;
  private Long postId;
  private Long parentId;

  private String nickname;
  private Visibility visibility;
  private String content;
  private int likeCount;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
