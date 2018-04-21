package com.rasto.accommodationreservationsystem.repository.entities;

import com.rasto.accommodationreservationsystem.repository.AccommodationTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class AccommodationType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AccommodationTypeEnum name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Accommodation> accommodations;

    public Integer getId() {
        return id;
    }

    public AccommodationTypeEnum getName() {
        return name;
    }

    public void setName(AccommodationTypeEnum name) {
        this.name = name;
    }

    public List<Accommodation> getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(List<Accommodation> accommodations) {
        this.accommodations = accommodations;
    }
}
