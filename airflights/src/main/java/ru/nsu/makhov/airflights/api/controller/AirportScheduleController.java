package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.CityDto;
import ru.nsu.makhov.airflights.api.dto.ScheduleEntryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AirportScheduleController {

    @Operation(summary = "Расписание прибытий в аэропорт")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает все прибытия в этот аэропорт"),
            @ApiResponse(responseCode = "404", description = "Аэропорт не найден")
    })
    @GetMapping(ApiPaths.INBOUND_SCHEDULE)
    public List<ScheduleEntryDto> getInboundSchedule(@PathVariable String airportCode){
        throw new UnsupportedOperationException();
    }

    @Operation(summary = "Расписание отбытий из аэропорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает все отбытия из этого аэропорта"),
            @ApiResponse(responseCode = "404", description = "Аэропорт не найден")
    })
    @GetMapping(ApiPaths.OUTBOUND_SCHEDULE)
    public List<ScheduleEntryDto> getOutboundSchedule(@PathVariable String airportCode){
        throw new UnsupportedOperationException();
    }
}
