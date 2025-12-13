package com.example.AppleStore_Project_JavaSpringBoot_CoLy.util;

import java.text.DecimalFormat;

public class MoneyUtil {

    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    public String vnd(Number number) {
        if (number == null) return "0 ₫";
        return formatter.format(number.longValue()) + " ₫";
    }
}
