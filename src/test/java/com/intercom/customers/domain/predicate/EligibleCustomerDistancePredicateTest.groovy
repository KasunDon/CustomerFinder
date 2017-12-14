package com.intercom.customers.domain.predicate

import com.intercom.customers.domain.entity.Customer
import com.intercom.customers.domain.entity.Location
import spock.lang.Specification

class EligibleCustomerDistancePredicateTest extends Specification {


    def officeLocation = Location.of( 53.339428, -6.257664)
    def eligibleDistanceInKm = 50
    def eligibleCustomerDistancePredicate =
        new EligibleCustomerDistancePredicate(
            officeLocation,
            eligibleDistanceInKm
        )

    def "EligibleCustomerDistancePredicate - should return true for customers within eligible distance"() {
        setup:

        def customer = Mock(Customer){
            getLocation() >> Location.of(53.1302756, -6.2397222)
        }

        when:
        boolean isCustomerWithinEligibleDistance = eligibleCustomerDistancePredicate.test(customer)

        then:
        isCustomerWithinEligibleDistance == true
    }

    def "EligibleCustomerDistancePredicate - should return false customers within eligible distance"() {
        setup:

        def customer = Mock(Customer){
            getLocation() >> Location.of(51.606635, -0.011131)
        }

        when:
        boolean isCustomerWithinEligibleDistance = eligibleCustomerDistancePredicate.test(customer)

        then:
        isCustomerWithinEligibleDistance == false
    }
}
