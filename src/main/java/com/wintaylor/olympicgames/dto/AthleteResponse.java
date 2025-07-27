package com.wintaylor.olympicgames.dto;

import lombok.Getter;

@Getter
public class AthleteResponse {
    private Double height;
    private Double weight;
    private double age;
    private Double bmi;

    public AthleteResponse(Double height, Double weight, double age, Double bmi) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.bmi = bmi;
    }
}