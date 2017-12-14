package com.intercom.customers.domain.predicate;

import com.intercom.customers.domain.compute.DistanceCalculator;
import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.domain.entity.Location;
import org.apache.log4j.Logger;

import java.util.function.Predicate;

public class EligibleCustomerDistancePredicate implements Predicate<Customer> {

    private static Logger LOGGER = Logger.getLogger(EligibleCustomerDistancePredicate.class);

    private Location officeLocation;
    private double eligibleDistanceInKm;

    public EligibleCustomerDistancePredicate(
        Location officeLocation,
        double eligibleDistanceInKm
    ) {
        this.officeLocation = officeLocation;
        this.eligibleDistanceInKm = eligibleDistanceInKm;
    }

    public boolean test(
        Customer customer
    ) {

        double distance =
            DistanceCalculator
                .computeToKilometers(
                    officeLocation,
                    customer.getLocation()
                );

        boolean isCustomerWithinEligibleDistance = (distance <= eligibleDistanceInKm);

        if (!isCustomerWithinEligibleDistance) {

            LOGGER
                .info(
                    " @@ skipping customer userId: " + customer.getUserId()
                        + ", distance: " + distance + "km. Due to not falling under eligible distance."
                );

        }

        return isCustomerWithinEligibleDistance;
    }
}
