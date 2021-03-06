package com.codecool.programmingschool;

import com.codecool.programmingschool.util.RandomGen;

import java.util.Objects;

public class Student extends Person {
    private Module currentModule;
    private int progress;
    private boolean onJobHunt;

    public Student(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
        this.currentModule = Module.PB;
        this.progress = 0;
        this.onJobHunt = false;
    }

    public Module getCurrentModule() {
        return currentModule;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isOnJobHunt() {
        return onJobHunt;
    }

    @Override
    public void update() {
        progress += Math.max(RandomGen.getRandomIntWithMeanAndStdDev(33, 13), 0);
        if (progress >= 100) {
            Module moduleToMoveTo = currentModule.nextModule();
            if (moduleToMoveTo != null) {
                progress = 0;
                currentModule = moduleToMoveTo;
            } else {
                progress = 100;

                // (student has effectively finished the courses and should look to get hired)
                onJobHunt = true;
            }
        }
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
