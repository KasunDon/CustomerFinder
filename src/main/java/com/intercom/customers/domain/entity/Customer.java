package com.intercom.customers.domain.entity;

public class Customer {

    private Integer userId;
    private String name;
    private Location location;

    public Customer(
        Integer userId,
        String name,
        Location location
    ) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be zero.");
        }

        if (name == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }

        if (location == null) {
            throw new IllegalArgumentException("location cannot be null.");
        }

        this.userId = userId;
        this.name = name;
        this.location = location;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
