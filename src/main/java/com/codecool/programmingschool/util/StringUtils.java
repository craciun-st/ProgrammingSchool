package com.codecool.programmingschool.util;

import java.time.LocalDate;

public class StringUtils {
    public static final LocalDate DUMMY_DATE = LocalDate.of(1111, 1,1);

    // a more sophisticated program would handle more complex edge cases and throw errors;
    // here, we return DUMMY_DATE if string is invalid
    public static LocalDate getLocalDateFromIsoString(String date) {
        String[] dateParts = date.split("-");
        if (dateParts.length == 3) {
            try {

                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                return LocalDate.of(year, month, day);

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                return DUMMY_DATE;
            }


        }
        else { return DUMMY_DATE; }
    }

    public static boolean isPhoneNumberLike(String someString) {
        return someString.matches("^(\\+?\\d{1,2}\\s)?\\(?\\d{2,4}\\)?[0-9 -]+$");
    }

    public static String stripNonNumericCharacters(String someString) {
        return  someString.replaceAll(
                "[^0-9]", "");
    }
}
