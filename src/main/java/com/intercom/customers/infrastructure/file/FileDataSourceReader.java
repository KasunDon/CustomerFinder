package com.intercom.customers.infrastructure.file;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileDataSourceReader {

    private static Logger LOGGER = Logger.getLogger(FileDataSourceReader.class);

    public Optional<Stream<String>> read(
        String path
    ) {

        try {

            File file = new File(path);

            InputStream inputStream =
                file.exists()?
                    new FileInputStream(file):
                    new ClassPathResource(path).getInputStream();

            BufferedReader bufferedReader =
                new BufferedReader(
                    new InputStreamReader(inputStream)
                );

            List<String> lines = new ArrayList<>();

            while (bufferedReader.ready()) {
                lines.add(bufferedReader.readLine());
            }

            bufferedReader.close();
            inputStream.close();

            return
                Optional.of(
                    lines.stream()
                );

        } catch (IOException e) {

            LOGGER.error("Error occurred while loading customers", e);

        }

        return Optional.empty();
    }
}
