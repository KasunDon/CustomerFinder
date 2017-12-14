package integration

import com.intercom.customers.domain.EligibleCustomerFinder
import integration.helper.ClassPathXmlApplicationContextFactory
import spock.lang.Specification

class EligibleCustomerFinderIntegrationTest extends Specification {

    def eligibleCustomerFinder

    def setup() {
        System.setProperty("spring.profiles.active", "test")
        def context = ClassPathXmlApplicationContextFactory.create()

        eligibleCustomerFinder = context.getBean(EligibleCustomerFinder.class)
    }

    def "EligibleCustomerFinder - try find eligible customers"() {

        when:

        def customers = eligibleCustomerFinder.find()

        then:
        customers.size() == 14
    }

    def "EligibleCustomerFinder - try find eligible customers from an empty file"() {

        setup:

        System.setProperty("file-path", "/data/emptyCustomers.json")
        when:

        def customers = eligibleCustomerFinder.find()

        then:
        customers.size() == 0
    }

    def cleanup() {
        System.getProperties().clear()
    }
}
