package main.foodie.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
  private List<T> content;        // 실제 데이터
  private long totalElements;     // 전체 데이터 수
  private int totalPages;         // 전체 페이지 수
  private int currentPage;        // 현재 페이지
  private int pageSize;           // 페이지 크기
  private boolean hasNext;        // 다음 페이지 존재 여부
  private boolean hasPrevious;    // 이전 페이지 존재 여부
  private boolean isFirst;        // 첫 번째 페이지 여부
  private boolean isLast;         // 마지막 페이지 여부

  // 팩토리 메서드
  public static <T> PageResponseDTO<T> of(List<T> content, long totalElements, PageRequestDTO pageRequest) {
    int totalPages = (int) Math.ceil((double) totalElements / pageRequest.getSize());
    boolean hasNext = pageRequest.getPage() < totalPages;
    boolean hasPrevious = pageRequest.getPage() > 1;

    return PageResponseDTO.<T>builder()
        .content(content)
        .totalElements(totalElements)
        .totalPages(totalPages)
        .currentPage(pageRequest.getPage())
        .pageSize(pageRequest.getSize())
        .hasNext(hasNext)
        .hasPrevious(hasPrevious)
        .isFirst(pageRequest.getPage() == 1)
        .isLast(pageRequest.getPage() == totalPages || totalPages == 0)
        .build();
  }
}
