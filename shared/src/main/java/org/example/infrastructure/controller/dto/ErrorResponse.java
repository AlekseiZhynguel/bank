package org.example.infrastructure.controller.dto;

public class ErrorResponse {
  private final String status = "error";
  private final int statusCode;
  private final ErrorDetail error;
  private final String documentation_url;

  public ErrorResponse(
      String errorCode,
      String errorMessage,
      int statusCode,
      String details,
      String path,
      String suggestion) {
    this.statusCode = statusCode;
    this.error = new ErrorDetail(errorCode, errorMessage, details, path, suggestion);
    this.documentation_url = "https://api.example.com/docs/errors";
  }

  public String getStatus() {
    return status;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public ErrorDetail getError() {
    return error;
  }

  public String getDocumentation_url() {
    return documentation_url;
  }
}
