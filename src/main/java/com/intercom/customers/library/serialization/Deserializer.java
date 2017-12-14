package com.intercom.customers.library.serialization;

public interface Deserializer<S, T> {

    T deserialize(
        S source
    );
}
