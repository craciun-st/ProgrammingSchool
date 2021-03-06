package com.codecool.programmingschool;

import com.codecool.programmingschool.util.RandomGen;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Mentor extends Staff{
    private Module module;
    private EnumSet<ProgrammingLanguage> languages;     // EnumSet is more performant for enums

    public Mentor(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
        this.languages = EnumSet.noneOf(ProgrammingLanguage.class); // empty set of ProgrammingLanguages
    }

    public Mentor(String name, String phoneNumber, String birthDateAsString, double salary) {
        super(name, phoneNumber, birthDateAsString, salary);
        this.languages = EnumSet.noneOf(ProgrammingLanguage.class); // empty set of ProgrammingLanguages
    }

    @Override
    public void update() {
        EnumSet<ProgrammingLanguage> stuffToLearn = this.getLanguagesYetToLearn();
        if (stuffToLearn.size() > 0) {
            int dieRoll = new Random().nextInt(100);
            if (dieRoll < 60) {
                ProgrammingLanguage newLanguage = RandomGen.getRandomElementFromEnumSet(stuffToLearn);
                this.learnLanguage(newLanguage);
            }
        }
    }

    public Module getModule() {
        return module;
    }

    public void learnLanguage(ProgrammingLanguage someLanguage) {
        languages.add(someLanguage);
    }

    public boolean knowsLanguage(ProgrammingLanguage someLanguage) {
        return languages.contains(someLanguage);
    }

    public boolean assignToModule(Module someModule) {
        if (knowsLanguage(someModule.language)) {
            this.module = someModule;
            return true;
        } else {
            return false;
        }
    }

    public String toShortenedString() {
        return this.getName();
    }

    public EnumSet<ProgrammingLanguage> getLanguagesYetToLearn() {
        return EnumSet.complementOf(languages);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mentor mentor = (Mentor) o;
        return module == mentor.module && languages.equals(mentor.languages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), module, languages);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "name=" + this.getName() +
                ", phoneNumber=" + this.getPhoneNumber() +
                ", birthDate=" + this.getBirthDate().toString() +
                ", module=" + (module == null ? "---" : module.toString()) +
                ", languages=" + languages.toString() +
                '}';
    }
}
