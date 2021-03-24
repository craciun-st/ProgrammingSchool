package com.codecool.programmingschool;

import java.util.EnumSet;
import java.util.Set;

public class Mentor extends Staff{
    private Module module;
    private EnumSet<ProgrammingLanguage> languages;     // EnumSet is more performant for enums

    public Mentor(String name, String phoneNumber, String birthDateAsString) {
        super(name, phoneNumber, birthDateAsString);
        this.languages = EnumSet.noneOf(ProgrammingLanguage.class); // empty set of ProgrammingLanguages
    }

    @Override
    public void update() {

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
}
