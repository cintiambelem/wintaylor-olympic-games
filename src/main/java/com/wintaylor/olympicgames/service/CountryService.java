package com.wintaylor.olympicgames.service;

import com.wintaylor.olympicgames.dto.CountryMaxBmiResponse;
import com.wintaylor.olympicgames.dto.CountrySportStatsResponse;
import com.wintaylor.olympicgames.model.Athlete;
import com.wintaylor.olympicgames.utils.MathUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final AthleteService athleteService;

    public CountryService(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    public Set<String> getSportsByCountry(String countryCode) {
        return athleteService.getAll().values().stream()
                .filter(a -> a.getCountry().equalsIgnoreCase(countryCode))
                .map(Athlete::getSport)
                .collect(Collectors.toSet());
    }

    public CountrySportStatsResponse getGenderStatsByCountryAndSport(String countryCode, String sport) {
        List<Athlete> filtered = athleteService.getAll().values().stream()
                .filter(a -> a.getCountry().equalsIgnoreCase(countryCode))
                .filter(a -> a.getSport().equalsIgnoreCase(sport))
                .collect(Collectors.toList());

        List<Athlete> males = filtered.stream()
                .filter(a -> a.getGender().equalsIgnoreCase("male"))
                .collect(Collectors.toList());

        List<Athlete> females = filtered.stream()
                .filter(a -> a.getGender().equalsIgnoreCase("female"))
                .collect(Collectors.toList());

        CountrySportStatsResponse response = new CountrySportStatsResponse();
        response.setMaleCount(males.size());
        response.setFemaleCount(females.size());
        response.setAverageMaleAge(calculateAverageAge(males));
        response.setAverageFemaleAge(calculateAverageAge(females));

        return response;
    }

    private double calculateAverageAge(@NotNull List<Athlete> athletes) {
        return MathUtils.round(
                athletes.stream()
                        .mapToDouble(Athlete::getAge)
                        .average()
                        .orElse(0.0),
                2
        );
    }

    public CountryMaxBmiResponse getCountryWithHighestBmiByContinentAndGender(String continent, String gender) {
        List<Athlete> filtered = athleteService.getAll().values().stream()
                .filter(a -> a.getContinent().equalsIgnoreCase(continent))
                .filter(a -> a.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());

        Map<String, Double> averageBmiByCountry = filtered.stream()
                .collect(Collectors.groupingBy(
                        Athlete::getCountry,
                        Collectors.averagingDouble(Athlete::getBmi)
                ));

        Optional<Map.Entry<String, Double>> maxEntry = averageBmiByCountry.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        CountryMaxBmiResponse response = new CountryMaxBmiResponse();

        if (maxEntry.isPresent()) {
            response.setCountry(maxEntry.get().getKey());
            response.setAverageBmi(MathUtils.round(maxEntry.get().getValue(), 2));
        } else {
            response.setCountry(null);
            response.setAverageBmi(0.0);
        }

        return response;
    }
}


