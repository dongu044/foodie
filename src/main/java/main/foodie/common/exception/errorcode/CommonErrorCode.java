package main.foodie.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

  VALUE_INVALID("VALUE_INVALID","error.common.value.invalid", HttpStatus.BAD_REQUEST),
  UPDATE_FAILED("UPDATE_FAILED","error.common.update.failed", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String messageKey;
  private final HttpStatus status;
}
