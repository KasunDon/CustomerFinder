package com.intercom.customers.domain

import com.intercom.customers.domain.entity.Customer
import com.intercom.customers.domain.persistence.CustomerDataLoader
import spock.lang.Specification

import java.util.function.Predicate

class EligibleCustomerFinderTest extends Specification {

    def customerDataLoader = Mock(CustomerDataLoader)
    def eligibleCustomerDistancePredicate = Mock(Predicate)
    def eligibleCustomerFinder =
        new EligibleCustomerFinder(
            customerDataLoader,
            eligibleCustomerDistancePredicate
        )

    def "EligibleCustomerFinder - should iterate through loaded customers and predicate eligible customers"() {

        setup:

        def customer1 = Mock(Customer) {
            getUserId() >> 1
        }

        def customer2 = Mock(Customer) {
            getUserId() >> 2
        }

        def customers = [customer1, customer2]

        when:
        def eligibleCustomers = eligibleCustomerFinder.find()

        then:

        1 * customerDataLoader.load() >> customers
        1 * eligibleCustomerDistancePredicate.test(_) >> true
        1 * eligibleCustomerDistancePredicate.test(_) >> false

        eligibleCustomers.size() == 1
    }

    def "EligibleCustomerFinder - should sort eligible customers into a map"() {

        setup:

        def customer1 = Mock(Customer) {
            getUserId() >> 100
        }

        def customer2 = Mock(Customer) {
            getUserId() >> 2
        }

        def customer3 = Mock(Customer) {
            getUserId() >> 54
        }

        def customer4 = Mock(Customer) {
            getUserId() >> 99
        }

        def customers = [customer1, customer2, customer3, customer4]

        when:
        def eligibleCustomers = eligibleCustomerFinder.find()

        then:

        1 * customerDataLoader.load() >> customers
        1 * eligibleCustomerDistancePredicate.test(_) >> true
        1 * eligibleCustomerDistancePredicate.test(_) >> false
        1 * eligibleCustomerDistancePredicate.test(_) >> true
        1 * eligibleCustomerDistancePredicate.test(_) >> true

        eligibleCustomers.size() == 3

        eligibleCustomers.entrySet().first().key == 54
        eligibleCustomers.entrySet().last().key == 100
    }

    def "EligibleCustomerFinder - should return an empty map when no customers are eligible"() {

        setup:

        def customer1 = Mock(Customer) {
            getUserId() >> 1
        }

        def customer2 = Mock(Customer) {
            getUserId() >> 2
        }

        def customers = [customer1, customer2]

        when:
        def eligibleCustomers = eligibleCustomerFinder.find()

        then:

        1 * customerDataLoader.load() >> customers
        1 * eligibleCustomerDistancePredicate.test(_) >> false
        1 * eligibleCustomerDistancePredicate.test(_) >> false

        eligibleCustomers.isEmpty() == true
    }

    def "EligibleCustomerFinder - should return an empty map when no customers are loaded"() {

        setup:

        def customers = []

        when:
        def eligibleCustomers = eligibleCustomerFinder.find()

        then:

        1 * customerDataLoader.load() >> customers
        0 * eligibleCustomerDistancePredicate.test(_)

        eligibleCustomers.isEmpty() == true
    }
}
