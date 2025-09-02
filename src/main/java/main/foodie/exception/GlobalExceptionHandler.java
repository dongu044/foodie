package main.foodie.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
    return buildErrorResponse(e.getErrorCode().getCode(), e.getMessage(), e.getErrorCode().getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(
      MethodArgumentNotValidException e) {
    List<String> errors = getValidationMessages(e);
    return buildErrorResponse("VALIDATION_ERROR", errors, HttpStatus.BAD_REQUEST);
  }

  private List<String> getValidationMessages(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAll(Exception e) {
    log.error("서버 내부 오류 발생", e);
    return buildErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<ErrorResponse> buildErrorResponse(String code, Object messageKey,
      HttpStatus status) {
    Object message;
    if (messageKey instanceof String) {
      message = messageSource.getMessage(messageKey.toString(), null,
          LocaleContextHolder.getLocale());
    } else {
      message = messageKey;
    }
    log.warn("비즈니스 예외 발생: 코드={}, 메시지={}", code, message);

    return ResponseEntity
        .status(status)
        .body(new ErrorResponse(code, message));
  }

  record ErrorResponse(String code, Object message) {

  }
}
