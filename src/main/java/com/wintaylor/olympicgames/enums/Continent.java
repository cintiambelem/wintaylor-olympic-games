package com.wintaylor.olympicgames.enums;

import lombok.Getter;

@Getter
public enum Continent {
    AFRICA("Africa"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    OCEANIA("Oceania");

    private final String value;

    Continent(String value) {
        this.value = value;
    }

}
