package com.wintaylor.olympicgames.controller;

import com.wintaylor.olympicgames.dto.ContinentAgeStatsResponse;
import com.wintaylor.olympicgames.service.ContinentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/continents")
public class ContinentController {

    private final ContinentService continentService;

    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping("/stats")
    @Operation(description = "Exercício 4")
    public ContinentAgeStatsResponse getPeopleCountByContinentAboveAge(
            @RequestParam String sport,
            @RequestParam double age
    ) {
        return continentService.getPeopleCountByContinentAboveAge(sport, age);
    }
}
