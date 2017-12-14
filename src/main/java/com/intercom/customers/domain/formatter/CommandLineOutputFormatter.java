package com.intercom.customers.domain.formatter;

import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.library.formatter.OutputFormatter;

import java.util.Map;

public class CommandLineOutputFormatter  implements OutputFormatter <Map<Integer, Customer>> {

    public void format(Map<Integer, Customer> customers) {

        System.out.println("============================================");

        System.out.println(
            "Found " + customers.size() + " customers within " +
                System.getProperty("app.defaultEligibleCustomerDistance.km") +
                " km radius."
        );

        System.out.println("============================================");

        customers
            .forEach(
                (id,customer) ->
                    System.out.println(
                        "UserId: " + id + ", Name: " + customer.getName()
                    )
            );
    }

}
