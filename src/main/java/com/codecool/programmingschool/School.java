package com.codecool.programmingschool;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class School {
    public final double BASE_SALARY = 1234.5;
    private Set<Staff> employees;
    private Set<Student> students;

    public School() {
        this.employees = new HashSet<>();
        this.students = new HashSet<>();
    }

    public void addStudent( Student someStudent) {
        if (someStudent != null) {
            students.add(someStudent);
        }
    }

    public void hireEmployee(Staff someEmployee) {
        if (someEmployee != null) {
            if (someEmployee.getSalary() == null) {
                double newSalary = decideSalaryForNewEmployee(someEmployee);
                someEmployee.setSalary(newSalary);
            }
            employees.add(someEmployee);
        }
    }

    public double decideSalaryForNewEmployee(Staff someEmployee) {

        // could have more complex logic here based on fields
        // of specific subclasses (Mentors, SalesPersons, etc...)

        return BASE_SALARY;
    }

    public Set<Mentor> getMentors() {
        Set<Mentor> mentors = new HashSet<>();
        for (Staff employee : employees) {
            if (employee instanceof Mentor) {
                Mentor currentMentor = (Mentor) employee;
                mentors.add(currentMentor);
            }
        }

        return mentors;
    }

    public Set<SalesPerson> getSalesPersons() {
        Set<SalesPerson> salesPeople = new HashSet<>();
        for (Staff employee : employees) {
            if (employee instanceof SalesPerson) {
                SalesPerson currentSalesPerson = (SalesPerson) employee;
                salesPeople.add(currentSalesPerson);
            }
        }

        return salesPeople;
    }

    public void listStudentsInModule( Module someModule) {
        Comparator<Student> progressComparator = Comparator
                .comparing(Student::getProgress);

        System.out.println("Students in "+someModule.toString()+": ");
        students.stream()
                .filter(student -> student.getCurrentModule().equals(someModule))
                .sorted(progressComparator)
                .map(Student::toShortenedString)
                .forEach(System.out::println);

        System.out.println();   // empty row separator

    }

}
