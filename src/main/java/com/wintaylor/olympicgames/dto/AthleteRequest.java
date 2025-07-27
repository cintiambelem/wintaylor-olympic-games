package com.wintaylor.olympicgames.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class AthleteRequest {
    @NotNull
    private Double height;
    @NotNull
    private Double weight;
    @NotNull
    private Double age;
    @NotNull
    private Double bmi;

    @NotBlank
    private String continent;
    @NotBlank
    private String country;
    @NotBlank
    private String gender;
    @NotBlank
    private String sport;
}
