package main.foodie.common.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

  String getCode();

  String getMessageKey();

  HttpStatus getStatus();

}
