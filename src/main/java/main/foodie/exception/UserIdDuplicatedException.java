package main.foodie.exception;

public class UserIdDuplicatedException extends BusinessException{

  public UserIdDuplicatedException() {
    super("USER_ID_DUPLICATED", "error.user.id.duplicated");
  }
}
