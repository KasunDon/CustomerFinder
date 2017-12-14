package com.intercom.customers.infrastructure.file

import spock.lang.Specification

class FileDataSourceReaderTest extends Specification {

    def fileDataSourceReader = Mock(FileDataSourceReader)

    def "FileDataSourceReader - should read file in classpath using given path"() {

        setup:
        def path = "/path/abc"
        def lines = ["line1"].stream()

        when:
        fileDataSourceReader.read(path)

        then:
        fileDataSourceReader.read(path) >> Optional.of(lines)
    }

    def "FileDataSourceReader - should return a optional stream when file doesn't contains lines"() {

        setup:
        def path = "/path/abc"

        when:
        fileDataSourceReader.read(path)

        then:
        fileDataSourceReader.read(path) >> Optional.empty()
    }
}
