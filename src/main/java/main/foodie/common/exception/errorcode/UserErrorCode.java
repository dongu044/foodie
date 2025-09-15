package main.foodie.common.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.foodie.common.exception.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {

  USERID_DUPLICATED("USERID_DUPLICATED","error.user.userid.duplicated", HttpStatus.BAD_REQUEST),
  NICKNAME_DUPLICATED("NICKNAME_DUPLICATED","error.user.nickname.duplicated", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND("USER_NOT_FOUND","error.user.notFound", HttpStatus.NOT_FOUND),
  USERID_INVALID("USERID_INVALID","error.user.userid.invalid", HttpStatus.BAD_REQUEST),
  PASSWORD_INVALID("PASSWORD_INVALID", "error.user.password.invalid", HttpStatus.BAD_REQUEST);

  private final String code;
  private final String messageKey;
  private final HttpStatus status;
}
