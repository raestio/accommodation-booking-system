package com.rasto.accommodationbookingsystem.backend.data.entity;

import com.rasto.accommodationbookingsystem.backend.data.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "UserInfo")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String surname;

    @NotEmpty
    @Size(max = 255, message = "Email should be valid")
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    @NotBlank
    @Size(max = 255)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @PrePersist
    @PreUpdate
    private void prepareData(){
        this.email = (email == null) ? email : email.toLowerCase();
        this.role = (role == null) ? Role.USER : role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
