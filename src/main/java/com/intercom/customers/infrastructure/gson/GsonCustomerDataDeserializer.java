package com.intercom.customers.infrastructure.gson;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.domain.entity.Location;
import com.intercom.customers.library.serialization.Deserializer;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

import java.util.Map;

public class GsonCustomerDataDeserializer implements Deserializer<String, Customer> {

    private static final Gson GSON =
        new GsonBuilder()
            .registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory())
            .disableHtmlEscaping()
            .create();

    public Customer deserialize(
        String source
    ) {

        Map<String, String> values =
            GSON.fromJson(source, new TypeToken<Map<String, String>>() {}.getType());

        if (values.size() == 0) {
            throw new IllegalStateException("Unable to deserialize customer data. source: " + source);
        }

        return new Customer(
            Integer.valueOf(values.get("user_id")),
            values.get("name"),
            Location.of(
                Double.valueOf(values.get("latitude")),
                Double.valueOf(values.get("longitude"))
            )
        );
    }
}
