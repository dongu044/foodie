package main.foodie.exception;

public class UserIdDuplicatedException extends BusinessException{

  public UserIdDuplicatedException() {
    super("MEMBER_ID_DUPLICATED", "error.user.id.duplicated");
  }
}
