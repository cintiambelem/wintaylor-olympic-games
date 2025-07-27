package com.wintaylor.olympicgames.service;

import com.wintaylor.olympicgames.dto.ContinentAgeStatsResponse;
import com.wintaylor.olympicgames.model.Athlete;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContinentService {

    private final AthleteService athleteService;

    public ContinentService(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    public ContinentAgeStatsResponse getPeopleCountByContinentAboveAge(String sport, double minAge) {
        List<Athlete> filtered = athleteService.getAll().values().stream()
                .filter(a -> a.getSport().equalsIgnoreCase(sport))
                .filter(a -> a.getAge() > minAge)
                .collect(Collectors.toList());

        Map<String, Long> counts = filtered.stream()
                .collect(Collectors.groupingBy(
                        Athlete::getContinent,
                        Collectors.counting()
                ));

        ContinentAgeStatsResponse response = new ContinentAgeStatsResponse();
        response.setSport(sport);
        response.setMinAge(minAge);
        response.setCountsByContinent(counts);

        return response;
    }
}
