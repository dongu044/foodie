package main.foodie.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

  String getCode();

  String getMessageKey();

  HttpStatus getStatus();

}
