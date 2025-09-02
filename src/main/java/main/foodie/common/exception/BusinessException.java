package main.foodie.common.exception;

import lombok.Getter;
import main.foodie.common.exception.errorcode.ErrorCode;

@Getter
public class BusinessException extends RuntimeException{

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessageKey());
    this.errorCode = errorCode;
  }
}
