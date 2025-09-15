package main.foodie.common.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements ErrorCode {

  POST_NOT_FOUND("POST_NOT_FOUND","error.board.post.notFound", HttpStatus.NOT_FOUND),
  POST_ACCESS_DENIED("POST_ACCESS_DENIED","error.board.post.accessDenied", HttpStatus.FORBIDDEN),
  COMMENT_NOT_FOUND("COMMENT_NOT_FOUND","error.board.comment.notFound", HttpStatus.NOT_FOUND),
  COMMENT_ACCESS_DENIED("COMMENT_ACCESS_DENIED","error.board.comment.accessDenied", HttpStatus.FORBIDDEN),
  REPLY_DEPTH_EXCEED("REPLY_DEPTH_EXCEED","error.board.comment.depthExceed", HttpStatus.FORBIDDEN);


  private final String code;
  private final String messageKey;
  private final HttpStatus status;
}
