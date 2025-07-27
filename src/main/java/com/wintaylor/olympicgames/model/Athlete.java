package com.wintaylor.olympicgames.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Athlete {
    private String continent;
    private String country;
    private double height;
    private double weight;
    @JsonProperty("BMI")
    private double bmi;
    private double age;
    private String gender;
    private String sport;

}
