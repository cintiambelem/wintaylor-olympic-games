package com.wintaylor.olympicgames.controller;

import com.wintaylor.olympicgames.dto.CountryMaxBmiResponse;
import com.wintaylor.olympicgames.dto.CountrySportStatsResponse;
import com.wintaylor.olympicgames.enums.Continent;
import com.wintaylor.olympicgames.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryCode}/sports")
    @Operation(description = "Exercício 2")
    public Set<String> getSportsByCountry(@PathVariable String countryCode) {
        return countryService.getSportsByCountry(countryCode);
    }

    @GetMapping("/{countryCode}/sports/{sport}/stats")
    @Operation(description = "Exercício 3")
    public CountrySportStatsResponse getStats(@PathVariable String countryCode, @PathVariable String sport) {
        return countryService.getGenderStatsByCountryAndSport(countryCode, sport);
    }

    @GetMapping("/max-bmi")
    @Operation(description = "Exercício 5")
    public CountryMaxBmiResponse getCountryWithMaxBmi(
            @RequestParam Continent continent,
            @RequestParam String gender
    ) {
        return countryService.getCountryWithHighestBmiByContinentAndGender(continent.getValue(), gender);
    }
}
