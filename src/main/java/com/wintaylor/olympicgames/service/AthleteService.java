package com.wintaylor.olympicgames.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wintaylor.olympicgames.model.Athlete;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.capitalize;

@Service
public class AthleteService {

    private final HashMap<String, Athlete> athleteMap = new HashMap<>();

    @PostConstruct
    public void loadData() {
        try {
            String url = "https://cdn.jsdelivr.net/gh/highcharts/highcharts@24912efc85/samples/data/olympic2012.json";
            ObjectMapper mapper = new ObjectMapper();
            List<Athlete> athletes = mapper.readValue(new URL(url), new TypeReference<List<Athlete>>() {
            });
            for (int i = 0; i < athletes.size(); i++) {
                athleteMap.put(String.valueOf(i), athletes.get(i));
            }
            System.out.println("Dados carregados: " + athleteMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Athlete> getAll() {
        return athleteMap;
    }

    public Athlete getByIndex(String index) {
        return athleteMap.get(index);
    }

    public void updateAthlete(String id, Map<String, Object> updates) {
        Athlete athlete = athleteMap.get(id);
        if (athlete == null) {
            throw new NoSuchElementException("Atleta não encontrado");
        }

        updates.forEach((field, value) -> {
            try {
                switch (field) {
                    case "height":
                    case "weight":
                    case "age":
                    case "bmi":
                        athlete.getClass()
                                .getMethod("set" + capitalize(field), double.class)
                                .invoke(athlete, castToDouble(value));
                        break;
                    case "continent":
                    case "country":
                    case "sport":
                    case "gender":
                        athlete.getClass()
                                .getMethod("set" + capitalize(field), String.class)
                                .invoke(athlete, value);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + field);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Map<String, Athlete> getPreSelectedForParisFootball() {
        return athleteMap.entrySet().stream()
            .filter(entry -> {
                Athlete athlete = entry.getValue();
                return "female".equalsIgnoreCase(athlete.getGender())
                        && !"football".equalsIgnoreCase(athlete.getSport())
                        && athlete.getAge() < 20;
            })
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Double castToDouble(Object value) {
        if (value instanceof Number) return ((Number) value).doubleValue();
        if (value instanceof String) return Double.parseDouble((String) value);
        throw new IllegalArgumentException("Value is invalid: " + value);
    }



}
