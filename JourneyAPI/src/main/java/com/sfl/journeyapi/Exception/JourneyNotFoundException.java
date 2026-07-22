package com.sfl.journeyapi.Exception;

public class JourneyNotFoundException extends RuntimeException {
    public JourneyNotFoundException(String message) {
        super(message);
    }
}