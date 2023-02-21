package com.test.bankapi.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-d";


    public static LocalDateTime parse(LocalDate date) {
        return date.atStartOfDay();
    }

    public static LocalDateTime getStartDate(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getEndDate(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
    }

    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }
}
