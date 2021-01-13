package danyliuk.mykola.exceptions;

/**
 * Причина помилки
 */
public enum ErrorReason {

  //for auth
  INTERNAL_SERVER_ERROR("Internal Server Error"),
  USER_CREDENTIALS_NOT_VALID("User name or password is not valid"),
  INVALID_TOKEN("Invalid token for user"),
  USER_NOT_FOUND("User not found"),

  // for Rewarding
  AUTHOR_LOW_BALANCE("Not enough reward point on balance of author."),
  RECIPIENT_LOW_BALANCE("Not enough reward point on balance of recipient."),
  INCORRECT_ROLE_FOR_REWARD("%s can`t do rewarding of %s. Employee only can be rewarded."),
  REWARD_OF_YOURSELF("You can`t do rewarding of yourself. Your name is [%s]."),
  NO_ENOUGH_POINTS_FOR_REWARDING("You haven`t enough point for rewarding. You limit is %s. You score for this employee %s"),
  SAME_DEPARTMENT("You have same department"),
  SAME_DEPARTMENT_AS_EXECUTIVE("This worker in you department"),
  TWICE_TIME_OF_REWARD("You rewarded this employee last time"),
  USER_NOT_AUTHORIZED("You are not authorized person to login");

  private String description;

  ErrorReason(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
