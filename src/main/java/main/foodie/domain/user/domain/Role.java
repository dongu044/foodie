package main.foodie.domain.user.domain;

public enum Role {
  MANAGER("매니저"),
  USER("일반회원");

  private final String description;

  Role(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
