# CustomerFinder - Finds customers within eligible distance.
This is a simple Java program to find customers within eligible distance then it's simply display customers sorted by their userId.

### Usage
Basic usage of this app is to run following command and It'll use embedded `customer.json` file in classpath.

```
java -D"spring.profiles.active=production" -jar target/customerfinder.jar com.intercom.customers.CustomerFinderApp
```

You can override default configuration such as `file-path` or allowed radius (`app.defaultEligibleCustomerDistance.km`) similar to following example.

```
java -D"spring.profiles.active=production" -D"app.defaultEligibleCustomerDistance.km=30" -D"file-path=/home/kasun.don/data/customers.json" -jar target/customerfinder.jar com.intercom.customers.CustomerFinderApp
```

### Requirements
This project requires following software versions or higher in order to compile, package and execute the JAR.

```
JDK 8 or higher
maven2 or higher
```

### Tests
If interested to run tests in this project, you could easily execute following commands.

##### Unit Test
```
mvn clean test
```

##### Integration Test
```
mvn clean verify
```

#### Packaging
Fat JAR can be easily build by running following `maven` command.

```
mvn clean verify package
```