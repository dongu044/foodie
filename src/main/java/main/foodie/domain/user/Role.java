package main.foodie.domain.user;

import lombok.Getter;

@Getter
public enum Role {
  MANAGER("매니저"),
  USER("일반회원");

  private final String description;

  Role(String description) {
    this.description = description;
  }
}
