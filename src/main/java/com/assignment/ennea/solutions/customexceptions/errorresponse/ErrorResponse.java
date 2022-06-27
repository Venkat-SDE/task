package com.assignment.ennea.solutions.customexceptions.errorresponse;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * ErrorResponse Class
 */
@Data
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private OffsetDateTime timeStamp;

}
