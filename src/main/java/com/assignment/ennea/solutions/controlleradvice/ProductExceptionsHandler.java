package com.assignment.ennea.solutions.controlleradvice;

import com.assignment.ennea.solutions.customexceptions.InValidDateException;
import com.assignment.ennea.solutions.customexceptions.errorresponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

/**
 * ExceptionHandler Class which handles the custom exceptions
 */
@ControllerAdvice
public class ProductExceptionsHandler {

    /**
     * @param inValidDateException it takes inValidDateException
     * @return then returns the info about the error occurred
     */
    @ExceptionHandler(InValidDateException.class)
    public ResponseEntity<ErrorResponse> handleInValidDateException(InValidDateException inValidDateException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("V111");
        errorResponse.setErrorMessage(inValidDateException.getMessage());
        errorResponse.setTimeStamp(OffsetDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
