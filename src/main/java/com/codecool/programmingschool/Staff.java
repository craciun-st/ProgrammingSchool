package com.codecool.programmingschool;

import java.util.Objects;

public abstract class Staff extends Person {
    private Double salary;

    // Staff that has not yet agreed upon a salary (will need to have the Double set)
    public Staff(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
    }

    // Staff that has agreed upon a salary
    public Staff(String name, String phoneNumber, String birthDateAsString, double salary) {
        super(name, phoneNumber, birthDateAsString);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Staff staff = (Staff) o;
        return Double.compare(staff.salary, salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }
}
