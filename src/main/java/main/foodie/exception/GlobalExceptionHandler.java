package main.foodie.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
    String message = messageSource.getMessage(e.getCode(), null, LocaleContextHolder.getLocale());
    return ResponseEntity
        .badRequest()
        .body(new ErrorResponse(e.getCode(), message));
  }

  record ErrorResponse(String code, String message) { }
}
