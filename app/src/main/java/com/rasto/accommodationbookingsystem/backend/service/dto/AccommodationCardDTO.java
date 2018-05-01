package com.rasto.accommodationbookingsystem.backend.service.dto;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class represents accommodation card that is displayed on home page
 */
public class AccommodationCardDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal pricePerNight;
    private String mainPhotoUrl;

    public static AccommodationCardDTO create(@NotNull Accommodation accommodation) {
        AccommodationCardDTO card = new AccommodationCardDTO();
        card.id = accommodation.getId();
        card.name = accommodation.getName();
        card.pricePerNight = accommodation.getPricePerNight();
        card.mainPhotoUrl = accommodation.getPhotos().get(0).getUrl();
        return card;
    }

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

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }
}
