package main.foodie.dto;

import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

  @Builder.Default
  private int page = 1;
  @Builder.Default
  private int size = 20;

  @Builder.Default
  private String sortBy = "created_at";
  @Builder.Default
  private String sortDir = "DESC";

  private String keyword;
  private String category;

  // 정렬 프리셋 맵
  private static final Map<String, String[]> SORT_PRESETS = Map.of(
      "popular", new String[]{"like_count", "DESC"},
      "recent", new String[]{"created_at", "DESC"},
      "trending", new String[]{"view_count", "DESC"},
      "hot", new String[]{"comment_count", "DESC"},
      "oldest", new String[]{"created_at", "ASC"}
  );

  public int getOffset() {
    return (page - 1) * size;
  }

  public String getSafeSort() {
    Set<String> allowedSorts = Set.of("created_at", "view_count", "like_count", "comment_count");
    return allowedSorts.contains(sortBy) ? sortBy : "created_at";
  }

  public String getSafeDir() {
    return "ASC".equalsIgnoreCase(sortDir) ? "ASC" : "DESC";
  }

  public static PageRequestDTO of(String type, int page, int size, String keyword, String category) {
    String[] preset = SORT_PRESETS.getOrDefault(type, new String[]{"created_at", "DESC"});
    return new PageRequestDTO(page, size, preset[0], preset[1], keyword, category);
  }

  public static PageRequestDTO of(String type) {
    return of(type, 1, 20, null, null);
  }

  public static PageRequestDTO of(String type, int page, int size) {
    return of(type, page, size, null, null);
  }
}
