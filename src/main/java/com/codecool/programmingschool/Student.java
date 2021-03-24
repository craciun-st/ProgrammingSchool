package com.codecool.programmingschool;

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
}
