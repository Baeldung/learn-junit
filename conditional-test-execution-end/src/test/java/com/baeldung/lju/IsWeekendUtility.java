package com.baeldung.lju;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class IsWeekendUtility {

    public static boolean isWeekend() {
        DayOfWeek today = LocalDate.now()
            .getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }

}