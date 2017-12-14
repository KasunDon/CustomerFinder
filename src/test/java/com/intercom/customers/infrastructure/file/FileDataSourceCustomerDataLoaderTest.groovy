package com.intercom.customers.infrastructure.file

import com.intercom.customers.domain.entity.Customer
import com.intercom.customers.infrastructure.gson.GsonCustomerDataDeserializer
import spock.lang.Specification

class FileDataSourceCustomerDataLoaderTest extends Specification {

    def fileDataSourceReader = Mock(FileDataSourceReader)
    def gsonCustomerDataDeserializer = Mock(GsonCustomerDataDeserializer)
    def fileDataSourceCustomerDataLoader =
        new FileDataSourceCustomerDataLoader(
            fileDataSourceReader,
            gsonCustomerDataDeserializer
        )

    def "FileDataSourceCustomerDataLoader - should load customer data from file and return as a List"() {

        setup:
        def customer1 = Mock(Customer)
        def customer2 = Mock(Customer)

        def lines = ["line1", "line2"].stream()

        when:
        def loadedCustomers = fileDataSourceCustomerDataLoader.load()

        then:

        1 * fileDataSourceReader.read(_) >> Optional.of(lines)
        1 * gsonCustomerDataDeserializer.deserialize(_) >> customer1
        1 * gsonCustomerDataDeserializer.deserialize(_) >> customer2

        loadedCustomers.size() == 2
        loadedCustomers.get(0) == customer1
        loadedCustomers.get(1) == customer2
    }

    def "FileDataSourceCustomerDataLoader - should return a empty list when data source file is empty"() {

        when:
        def loadedCustomers = fileDataSourceCustomerDataLoader.load()

        then:

        1 * fileDataSourceReader.read(_) >> Optional.empty()
        0 * gsonCustomerDataDeserializer.deserialize(_)

        loadedCustomers.empty == true
    }
}
