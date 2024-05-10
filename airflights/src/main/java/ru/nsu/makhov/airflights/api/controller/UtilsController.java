package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.AirportDto;
import ru.nsu.makhov.airflights.api.dto.CityDto;
import ru.nsu.makhov.airflights.core.repository.UtilsRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtilsController {

    private final UtilsRepository utilsRepository;

    @Operation(summary = "Все доступные города отправления и назначения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает все города")
    })
    @GetMapping(ApiPaths.CITY)
    public List<CityDto> getAllCities(){
        return utilsRepository.getAllCities();
    }

    @Operation(summary = "Все доступные аэропорты отправления и назначения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает все аэропорты")
    })
    @GetMapping(ApiPaths.AIRPORT)
    public List<AirportDto> getAllAirports(){
        return utilsRepository.getAllAirports();
    }

    @Operation(summary = "Все доступные аэропорты в городе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Возвращает все аэропорты города")
    })
    @GetMapping(ApiPaths.AIRPORT_IN_CITY)
    public List<AirportDto> getAllAirportsInCity(@PathVariable String city){
        return utilsRepository.getAllAirportsInCity(city);
    }
}
