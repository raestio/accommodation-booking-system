package com.rasto.accommodationbookingsystem.web.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.REST_ACCOMMODATION_COULD_NOT_FOUND_MESSAGE;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccommodationNotFoundException extends RuntimeException {

    public AccommodationNotFoundException(String accommodationID) {
        super(REST_ACCOMMODATION_COULD_NOT_FOUND_MESSAGE + "'" + accommodationID + "'");
    }

}
