package com.codecool.programmingschool;

public abstract class Staff extends Person {
    private double salary;

    public Staff(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
    }

    public Staff(String name, String phoneNumber, String birthDateAsString, double salary) {
        super(name, phoneNumber, birthDateAsString);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
