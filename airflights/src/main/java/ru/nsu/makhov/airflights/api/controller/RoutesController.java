package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.BookingClass;
import ru.nsu.makhov.airflights.api.dto.RouteDto;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutesController {

    @GetMapping(ApiPaths.ROUTE_FINDER)
    @Operation(summary = "Find routes between two points")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Source or destination does not exist")
    })
    List<RouteDto> getRoutes(
            @Parameter(description = "Source airport or city") @PathVariable String source,
            @Parameter(description = "Destination airport or city") @PathVariable String destination,
            @Parameter(description = "Departure date") @PathVariable Date date,
            @Parameter(description = "ECONOMY, COMFORT, BUSINESS")
            @RequestParam(required = false, defaultValue = "ECONOMY") BookingClass bookingClass,
            @Parameter(description = "Max leg count, 0 = unbound")
            @RequestParam(required = false, defaultValue = "1") Integer legCount
    ) {
        throw new UnsupportedOperationException();
    }
}
