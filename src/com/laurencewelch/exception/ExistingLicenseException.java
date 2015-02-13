package com.laurencewelch.exception;

/**
 * Created by laurence on 2/7/15.
 */
public class ExistingLicenseException extends Exception {
  public ExistingLicenseException() {
    super();
  }

  public ExistingLicenseException(String message) {
    super(message);
  }

  public ExistingLicenseException(String message, Throwable cause) {
    super(message, cause);
  }

  public ExistingLicenseException(Throwable cause) {
    super(cause);
  }
}
