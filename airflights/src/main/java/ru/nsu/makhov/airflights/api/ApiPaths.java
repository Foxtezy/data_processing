package ru.nsu.makhov.airflights.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiPaths {
    public static final String API_PREFIX = "/api";

    public static final String AIRPORT = API_PREFIX + "/airport";

    public static final String CITY = API_PREFIX + "/city";

    public static final String AIRPORT_IN_CITY = API_PREFIX + "/airport/{city}";

    public static final String INBOUND_SCHEDULE = API_PREFIX + "/schedule/inbound/{airportCode}";

    public static final String OUTBOUND_SCHEDULE = API_PREFIX + "/schedule/outbound/{airportCode}";

    public static final String ROUTE_FINDER = API_PREFIX + "/route/{source}/{destination}/{date}";

    public static final String BOOKING = API_PREFIX + "/booking";

    public static final String CHECKIN = API_PREFIX + "/checkin";
}
