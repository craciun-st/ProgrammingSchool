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
}
