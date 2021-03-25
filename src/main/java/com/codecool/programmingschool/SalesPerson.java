package com.codecool.programmingschool;

public class SalesPerson extends Staff {

    public SalesPerson(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
    }

    public SalesPerson(String name, String phoneNumber, String birthDateAsString, double salary) {
        super(name, phoneNumber, birthDateAsString, salary);
    }

    @Override
    public void update() {

    }

    @Override
    public String toString() {
        return "SalesPerson{" +
                "name=" + this.getName() +
                ", phoneNumber=" + this.getPhoneNumber() +
                ", birthDate=" + this.getBirthDate().toString() +
                '}';
    }
}
