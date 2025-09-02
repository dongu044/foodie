package main.foodie.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.exception.errorcode.ErrorCode;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessageKey());
    this.errorCode = errorCode;
  }
}
