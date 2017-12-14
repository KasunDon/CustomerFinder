package com.intercom.customers.domain.compute

import com.intercom.customers.domain.entity.Location
import spock.lang.Specification

class DistanceCalculatorTest extends Specification {

    def "DistanceCalculator - should be able to compute distance in kilometers"() {

        expect:

        def location1 = new Location(latitude1, longitude1)
        def location2 = new Location(latitude2, longitude2)

        DistanceCalculator.computeToKilometers(location1, location2) == distance

        where:

        latitude1 | longitude1 | latitude2 | longitude2 | distance
        51.606635 | -0.011131  | 51.641532 | -0.011587  | 3.88
        51.507478 | -0.067547  | 51.145549 | -0.185560  | 41.07
        0         | 0          | 51.606635 | -0.011131  | 5738.12
        50.814394 | -0.575575  | 51.762520 | -0.103940  | 110.4
        51.900715 | -5.249269  | 52.566052 |  1.733440  | 481.03
    }
}
