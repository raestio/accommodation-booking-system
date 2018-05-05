package com.rasto.accommodationbookingsystem.backend.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class AccommodationDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal pricePerNight;
    private int guests;
    private int beds;
    private int bathrooms;
    private AccommodationTypeEnumDTO typeName;
    private AddressDTO address;
    private Collection<PhotoDTO> photos;

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

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public AccommodationTypeEnumDTO getTypeName() {
        return typeName;
    }

    public void setTypeName(AccommodationTypeEnumDTO type) {
        this.typeName = type;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Collection<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoDTO> photos) {
        this.photos = photos;
    }
}
