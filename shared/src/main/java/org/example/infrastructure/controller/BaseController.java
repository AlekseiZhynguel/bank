package org.example.infrastructure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.UUID;
import org.example.infrastructure.controller.dto.ErrorResponse;
import org.example.infrastructure.controller.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

  protected <T> ResponseEntity<String> buildSuccessResponseJsonResponse(T reponseObject) {
    SuccessResponse<T> successResponse =
        new SuccessResponse<>(
            HttpStatus.OK.value(),
            reponseObject,
            UUID.randomUUID().toString(),
            "https://api.example.com/docs");
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(convertObjectToJsonString(successResponse));
  }

  protected ResponseEntity<String> buildErrorResponse(
      String errorCode,
      String errorMessage,
      int statusCode,
      String details,
      String path,
      String suggestion) {
    ErrorResponse errorResponse =
        new ErrorResponse(errorCode, errorMessage, statusCode, details, path, suggestion);

    return ResponseEntity.status(HttpStatus.valueOf(statusCode))
        .contentType(MediaType.APPLICATION_JSON)
        .body(convertObjectToJsonString(errorResponse));
  }

  protected String convertObjectToJsonString(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    try {
      return mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
