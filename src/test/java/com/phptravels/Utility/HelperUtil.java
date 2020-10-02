package com.phptravels.Utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperUtil {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String getStartDate(){
        return LocalDate.now().plusDays(1).format(format);
    }

    public static String getEndDate(int days) {
        return LocalDate.parse(getStartDate(),format).plusDays(days).format(format);
    }
}
