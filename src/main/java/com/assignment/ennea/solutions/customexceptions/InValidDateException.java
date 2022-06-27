package com.assignment.ennea.solutions.customexceptions;

/**
 * InValidDateException Class
 */
public class InValidDateException extends RuntimeException {

    /**
     * @param errorMessage it takes errorMessage as input
     */
    public InValidDateException(String errorMessage) {
        super(errorMessage);
    }
}
