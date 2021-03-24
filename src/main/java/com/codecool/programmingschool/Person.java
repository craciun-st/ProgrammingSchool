package com.codecool.programmingschool;

import com.codecool.programmingschool.util.StringUtils;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;

    public abstract void update();

    public Person(String name, String phoneNumber, String birthDateAsString) {
        this.name = name;
        if (StringUtils.isPhoneNumberLike(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = StringUtils.stripNonNumericCharacters(phoneNumber);
        }
        this.birthDate = StringUtils.getLocalDateFromIsoString(birthDateAsString);
    }
}
