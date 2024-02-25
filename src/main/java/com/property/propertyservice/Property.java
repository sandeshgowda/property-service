package com.property.propertyservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String address;
    private final String owner;

    private final LocalDate date;
    private final boolean occupied;

    public Property(long l, String s, String johnDoe, LocalDate now, boolean b) {
        id = l;
        address = s;
        owner = johnDoe;
        date = now;
        occupied = b;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isOccupied() {
        return occupied;
    }


}
