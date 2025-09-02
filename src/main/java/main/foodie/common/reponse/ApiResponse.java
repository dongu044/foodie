package main.foodie.common.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
public record ApiResponse<T>(String message, T data,
                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                             LocalDateTime timestamp) {

  public ApiResponse(String message) {
    this(message, null, LocalDateTime.now());
  }

  public ApiResponse(String message, T data) {
    this(message, data, LocalDateTime.now());
  }
}
