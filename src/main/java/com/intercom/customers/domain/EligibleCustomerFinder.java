package com.intercom.customers.domain;

import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.domain.persistence.CustomerDataLoader;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EligibleCustomerFinder {

    private CustomerDataLoader customerDataLoader;
    private Predicate<Customer> eligibleCustomerDistancePredicate;

    public EligibleCustomerFinder(
        CustomerDataLoader customerDataLoader,
        Predicate<Customer> eligibleCustomerDistancePredicate
    ) {
        this.customerDataLoader = customerDataLoader;
        this.eligibleCustomerDistancePredicate = eligibleCustomerDistancePredicate;

    }

    public Map<Integer, Customer> find() {

        Map<Integer, Customer> eligibleCustomers =
            customerDataLoader
                .load()
                .stream()
                .filter(eligibleCustomerDistancePredicate::test)
                .collect(
                    Collectors
                        .toMap(
                            customer -> customer.getUserId(),
                            customer -> customer
                        )
                );

        return new TreeMap<>(eligibleCustomers);
    }
}
