package ru.nsu.makhov.airflights.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.makhov.airflights.api.ApiPaths;
import ru.nsu.makhov.airflights.api.dto.CheckInDto;
import ru.nsu.makhov.airflights.api.dto.SeatNumberDto;

@RestController
@RequiredArgsConstructor
public class CheckInController {

    @Operation(summary = "Online check-in for a flight")
    @ApiResponses({
            @ApiResponse(description = "Seat number", responseCode = "200"),
            @ApiResponse(description = "Wrong booking id or seat", responseCode = "404")
    })
    @PostMapping(ApiPaths.CHECKIN)
    public SeatNumberDto checkin(@RequestBody CheckInDto checkInDto){
        throw new UnsupportedOperationException();
    }
}
