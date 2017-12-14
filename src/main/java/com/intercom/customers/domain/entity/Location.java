package com.intercom.customers.domain.entity;

public class Location {

    private Double latitude;
    private Double longitude;

    public Location(
        Double latitude,
        Double longitude
    ) {
        if (latitude == null) {
            throw new IllegalArgumentException("latitude cannot be null.");
        }

        if (longitude == null) {
            throw new IllegalArgumentException("longitude cannot be null.");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public static Location of(
        Double latitude,
        Double longitude
    ) {
        return new Location(latitude, longitude);
    }
}
