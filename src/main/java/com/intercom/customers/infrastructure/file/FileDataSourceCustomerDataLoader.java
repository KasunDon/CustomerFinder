package com.intercom.customers.infrastructure.file;

import com.intercom.customers.domain.entity.Customer;
import com.intercom.customers.domain.persistence.CustomerDataLoader;
import com.intercom.customers.library.serialization.Deserializer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataSourceCustomerDataLoader implements CustomerDataLoader {

    private FileDataSourceReader fileDataSourceReader;
    private Deserializer<String, Customer> customerDataDeserializer;

    public FileDataSourceCustomerDataLoader(
        FileDataSourceReader fileDataSourceReader,
        Deserializer<String, Customer> customerDataDeserializer
    ) {
        this.fileDataSourceReader = fileDataSourceReader;
        this.customerDataDeserializer = customerDataDeserializer;
    }

    public List<Customer> load() {

        String externalFilePath = System.getProperty("file-path");

        String filePath =
            externalFilePath == null || externalFilePath.isEmpty()?
                "/data/customers.json":
                externalFilePath;

        Optional<Stream<String>> lines =
            fileDataSourceReader
                .read(filePath);

        if (!lines
            .isPresent()) {
            return Collections.emptyList();
        }

        return
            lines
                .get()
                .map(customerDataDeserializer::deserialize)
                .collect(Collectors.toList());
    }
}
