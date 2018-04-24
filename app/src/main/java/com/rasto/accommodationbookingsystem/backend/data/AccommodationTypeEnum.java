package com.rasto.accommodationbookingsystem.backend.data;

public enum AccommodationTypeEnum {
    APARTMENT("Apartment"),
    HOUSE("House"),
    CONDOMINIUM("Condominium"),
    BUNGALOW("Bungalow"),
    COTTAGE("Cottage"),
    HOUSEBOAT("Houseboat"),
    FARM_STAY("Farm stay"),
    VILLA("Villa"),
    LIGHTHOUSE("Lighthouse");

    private String name;

    AccommodationTypeEnum(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
