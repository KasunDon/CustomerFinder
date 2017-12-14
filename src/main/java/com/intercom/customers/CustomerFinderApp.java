package com.intercom.customers;

import com.intercom.customers.domain.EligibleCustomerFinder;
import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.infrastructure.spring.ClassPathXmlApplicationContextFactory;
import com.intercom.customers.library.formatter.OutputFormatter;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class CustomerFinderApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = ClassPathXmlApplicationContextFactory.getOrCreate();

        EligibleCustomerFinder eligibleCustomerFinder =
            applicationContext
                .getBean(EligibleCustomerFinder.class);

        OutputFormatter<Map<Integer, Customer>> outputFormatter =
            applicationContext
                .getBean(OutputFormatter.class);

        outputFormatter
            .format(
                eligibleCustomerFinder
                    .find()
            );
    }
}
