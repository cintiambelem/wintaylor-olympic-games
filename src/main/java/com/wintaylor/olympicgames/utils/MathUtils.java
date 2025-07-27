package com.wintaylor.olympicgames.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
    public static double round(double value, int scale) {
        return BigDecimal.valueOf(value)
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
