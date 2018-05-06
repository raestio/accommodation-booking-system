package com.rasto.accommodationbookingsystem.backend.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class represents accommodation card that is displayed on home page
 */
public class AccommodationCardDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal pricePerNight;
    private PhotoDTO photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }
}
