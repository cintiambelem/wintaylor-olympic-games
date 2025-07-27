package com.wintaylor.olympicgames.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryMaxBmiResponse {
    private String country;
    private double averageBmi;
}
