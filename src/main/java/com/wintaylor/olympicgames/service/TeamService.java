package com.wintaylor.olympicgames.service;

import com.wintaylor.olympicgames.dto.AthleteFilterRequest;
import com.wintaylor.olympicgames.dto.AthleteRequest;
import com.wintaylor.olympicgames.model.Athlete;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final AthleteService athleteService;

    public TeamService(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    public void addAthleteToTeam(AthleteRequest request) {
        if (!"BRA".equalsIgnoreCase(request.getCountry())
                || !"male".equalsIgnoreCase(request.getGender())
                || !"football".equalsIgnoreCase(request.getSport())) {
            throw new IllegalArgumentException("Only male football athletes from Brazil can be added to the team.");
        }

        Athlete newAthlete = new Athlete();
        newAthlete.setContinent(request.getContinent());
        newAthlete.setCountry(request.getCountry());
        newAthlete.setGender(request.getGender());
        newAthlete.setSport(request.getSport());
        newAthlete.setHeight(request.getHeight());
        newAthlete.setWeight(request.getWeight());
        newAthlete.setAge(request.getAge());
        newAthlete.setBmi(request.getBmi());

        String newId = String.valueOf(athleteService.getAll().size());
        athleteService.getAll().put(newId, newAthlete);
    }

    public Map<String, Athlete> addAndPreselectParisAthletes(@NotNull AthleteRequest request) {
        Athlete newAthlete = new Athlete();
        newAthlete.setContinent(request.getContinent());
        newAthlete.setCountry(request.getCountry());
        newAthlete.setGender(request.getGender());
        newAthlete.setSport(request.getSport());
        newAthlete.setAge(request.getAge());
        newAthlete.setHeight(request.getHeight());
        newAthlete.setWeight(request.getWeight());
        newAthlete.setBmi(request.getBmi());

        Map<String, Athlete> athletes = athleteService.getAll();
        String newId = String.valueOf(athletes.size());
        athletes.put(newId, newAthlete);

        return athleteService.getPreSelectedForParisFootball();
    }

    public List<Athlete> filterAthletes(AthleteFilterRequest filter) {
        return athleteService.getAll().values().stream()
                .filter(a -> filter.getCountry() == null || a.getCountry().equalsIgnoreCase(filter.getCountry()))
                .filter(a -> filter.getSport() == null || a.getSport().equalsIgnoreCase(filter.getSport()))
                .filter(a -> filter.getGender() == null || a.getGender().equalsIgnoreCase(filter.getGender()))
                .collect(Collectors.toList());
    }

}
