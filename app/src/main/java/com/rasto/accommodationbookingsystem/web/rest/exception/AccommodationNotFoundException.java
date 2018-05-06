package com.rasto.accommodationbookingsystem.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(String accommodationID) {
        super("Could not find accommodation with id '" + accommodationID + "'");
    }

}
