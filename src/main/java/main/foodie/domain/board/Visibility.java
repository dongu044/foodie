package main.foodie.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Visibility {
  PUBLIC("공개"), PRIVATE("비밀");

  private final String description;
}
