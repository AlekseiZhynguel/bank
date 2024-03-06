package org.example.infrastructure.controller.dto;

public class SuccessResponse<T> {
  private final String status = "success";
  private final int statusCode;
  private final T payload;
  private final String requestId;
  private final String documentation_url;

  public SuccessResponse(int statusCode, T payload, String requestId, String documentation_url) {
    this.statusCode = statusCode;
    this.payload = payload;
    this.requestId = requestId;
    this.documentation_url = documentation_url;
  }

  public String getStatus() {
    return status;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public T getPayload() {
    return payload;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getDocumentation_url() {
    return documentation_url;
  }
}
