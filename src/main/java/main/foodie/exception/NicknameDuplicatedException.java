package main.foodie.exception;

public class NicknameDuplicatedException extends BusinessException{

  public NicknameDuplicatedException() {
    super("NICKNAME_DUPLICATED", "error.user.nickname.duplicated");
  }
}
