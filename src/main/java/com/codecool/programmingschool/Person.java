package com.codecool.programmingschool;

import com.codecool.programmingschool.util.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && phoneNumber.equals(person.phoneNumber) && birthDate.equals(person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, birthDate);
    }
}
