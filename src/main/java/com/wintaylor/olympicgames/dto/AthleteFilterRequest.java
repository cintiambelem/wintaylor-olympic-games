package com.wintaylor.olympicgames.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AthleteFilterRequest {
    private String country;
    private String sport;

    @Pattern(regexp = "male|female", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Gender must be 'male' or 'female'")
    private String gender;
}
