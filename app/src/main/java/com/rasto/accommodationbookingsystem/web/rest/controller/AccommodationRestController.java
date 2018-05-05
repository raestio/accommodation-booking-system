package com.rasto.accommodationbookingsystem.web.rest.controller;

import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/accommodations")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    Collection<AccommodationDTO> getAccommodations() {
        return accommodationService.findAllAndConvertToDTO();
    }
}
