package com.wintaylor.olympicgames.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountrySportStatsResponse {
    private int maleCount;
    private int femaleCount;
    private double averageMaleAge;
    private double averageFemaleAge;
}
