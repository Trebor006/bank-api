package com.test.bankapi.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {

    public static LocalDateTime getStartDate(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getEndDate(LocalDateTime date) {
        return LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
    }

}
