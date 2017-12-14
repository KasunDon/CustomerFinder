package com.intercom.customers.infrastructure.gson

import com.google.gson.JsonSyntaxException
import com.intercom.customers.domain.entity.Customer
import com.intercom.customers.domain.entity.Location
import spock.lang.Specification

class GsonCustomerDataDeserializerTest extends Specification {

    def gsonCustomerDataDeserializer = new GsonCustomerDataDeserializer()

    def "GsonCustomerDataDeserializer - should be able to deserialize valid customer data"() {

        setup:
        def json = "{\"latitude\": \"52.833502\", \"user_id\": 1, \"name\": \"ABC DEF\", \"longitude\": \"-8.522366\"}"

        when:
        def customer = gsonCustomerDataDeserializer.deserialize(json)

        then:
        customer instanceof Customer

        customer.userId == 1
        customer.name == "ABC DEF"

        customer.location instanceof Location
        customer.location.latitude == 52.833502
        customer.location.longitude == -8.522366
    }

    def "GsonCustomerDataDeserializer - should not deserialize properly if customer info is missing"() {

        setup:
        def json = "{}"

        when:
        gsonCustomerDataDeserializer.deserialize(json)

        then:
        thrown(IllegalStateException)
    }

    def "GsonCustomerDataDeserializer - should throw JsonSyntaxException when trying to deserialize invalid syntax"() {

        setup:
        def json = "{,"

        when:
        gsonCustomerDataDeserializer.deserialize(json)

        then:
        thrown(JsonSyntaxException)
    }
}
