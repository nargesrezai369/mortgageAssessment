package com.example.mortgageassessment.exceptions;

/**
 * This exception happen when Interest rate isn't found in the in-memory models.
 *
 * @author : Narges Rezaei
 **/
public class InterestRateNotFoundException extends RuntimeException {

    public InterestRateNotFoundException(String msg) {
        super(msg);
    }

}
