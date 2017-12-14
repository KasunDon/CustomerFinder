package com.intercom.customers.domain.persistence;

import com.intercom.customers.domain.entity.Customer;

import java.util.List;

public interface CustomerDataLoader {

    List<Customer> load();
}
