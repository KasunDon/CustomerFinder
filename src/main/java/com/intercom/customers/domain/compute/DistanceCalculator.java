package com.intercom.customers.domain.compute;

import com.intercom.customers.domain.entity.Location;

import java.math.BigDecimal;

public class DistanceCalculator {

    public static double computeToKilometers(
        Location location1,
        Location location2
    ) {
        double theta = location1.getLongitude() - location2.getLongitude();

        double distance =
            Math.sin(Math.toRadians(location1.getLatitude())) * Math.sin(Math.toRadians(location2.getLatitude())) +
                Math.cos(Math.toRadians(location1.getLatitude())) * Math.cos(Math.toRadians(location2.getLatitude())) *
                    Math.cos(Math.toRadians(theta));

        return
            BigDecimal
                .valueOf(
                    Math.toDegrees(Math.acos(distance)) * 60 * 1.1515 * 1.609344
                )
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }
}
