package com.codecool.programmingschool;

import java.util.Objects;

public class Student extends Person {
    private Module currentModule;
    private int progress;

    public Student(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
        this.currentModule = Module.PB;
        this.progress = 0;
    }

    public Module getCurrentModule() {
        return currentModule;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public void update() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return progress == student.progress && currentModule == student.currentModule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentModule, progress);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + this.getName() +
                ", phoneNumber=" + this.getPhoneNumber() +
                ", birthDate=" + this.getBirthDate().toString() +
                ", currentModule=" + currentModule +
                ", progress=" + progress +
                '}';
    }

    public String toShortenedString() {
        return this.getName() +
                "{progress=" + progress +
                '}';
    }
}
