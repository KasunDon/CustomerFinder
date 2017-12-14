package integration

import com.intercom.customers.domain.EligibleCustomerFinder
import com.intercom.customers.library.formatter.OutputFormatter
import integration.helper.ClassPathXmlApplicationContextFactory
import spock.lang.Specification

class ConfigurationIntegrationTest extends Specification {

    def setup() {
        System.setProperty("spring.profiles.active", "test")
    }

    def "The DI configuration loads correctly"() {
        setup:
        def applicationContext = ClassPathXmlApplicationContextFactory.create()

        when:
        applicationContext.getBean(EligibleCustomerFinder.class)
        applicationContext.getBean(OutputFormatter.class);

        then:
        noExceptionThrown()
    }
}
