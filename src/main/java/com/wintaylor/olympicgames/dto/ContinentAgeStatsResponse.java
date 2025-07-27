package com.wintaylor.olympicgames.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ContinentAgeStatsResponse {
    private String sport;
    private double minAge;
    private Map<String, Long> countsByContinent;
}
