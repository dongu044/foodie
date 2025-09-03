package main.foodie.common.exception.errorcode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.common.exception.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements ErrorCode {

  POST_NOT_FOUND("POST_NOT_FOUND","error.board.post.notfound", HttpStatus.BAD_REQUEST),
  POST_UPDATE_FAILED("POST_UPDATE_FAILED","error.board.post.update.failed", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String messageKey;
  private final HttpStatus status;
}
