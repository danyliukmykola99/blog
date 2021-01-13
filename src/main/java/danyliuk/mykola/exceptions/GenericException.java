package danyliuk.mykola.exceptions;

import org.apache.commons.lang3.StringUtils;

public abstract class GenericException extends RuntimeException {

  private static final long serialVersionUID = 8253360261424020850L;

  private StringBuilder context;
  protected ErrorReason errorReason;

  public Object errorDetails;

  public GenericException(ErrorReason errorReason, Object... reasonParams) {
    super((reasonParams == null || (reasonParams.length) == 0) ?
        errorReason.getDescription() : String.format(errorReason.getDescription(), reasonParams));
    this.errorReason = errorReason;
    this.errorDetails = reasonParams;
  }

  public String getContext() {
    if (context == null) {
      return StringUtils.EMPTY;
    } else {
      return context.toString();
    }
  }

}

