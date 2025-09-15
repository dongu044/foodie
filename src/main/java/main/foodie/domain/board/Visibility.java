package main.foodie.domain.board;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import main.foodie.common.exception.BusinessException;
import main.foodie.common.exception.errorcode.CommonErrorCode;

@RequiredArgsConstructor
@Getter
public enum Visibility {
  PUBLIC(1,"공개"), PRIVATE(2,"비밀");

  private final int code;
  private final String description;

  private static final Map<Integer, Visibility> CODE_MAP =
      Arrays.stream(values())
          .collect(Collectors.toMap(Visibility::getCode, Function.identity()));

  public static Visibility fromCode(int code) {
    Visibility visibility = CODE_MAP.get(code);
    if (visibility == null) {
      throw new BusinessException(CommonErrorCode.VALUE_INVALID);
    }
    return visibility;
  }
}
