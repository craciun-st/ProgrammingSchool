package com.codecool.programmingschool;

import com.codecool.programmingschool.factories.PersonFactory;

import java.util.EnumSet;

public class MainApp {
    public static final int TIME_DURATION = 12;
    public static void main(String[] args) {
        // init
        School school = new School();
        school.hireEmployee(PersonFactory.createRandomFullyKnowledgeableMentor());
        for (int i = 0; i < 3; i++) {
            school.addStudent(PersonFactory.createRandomStudent());
        }
        EnumSet<Module> allModules = EnumSet.allOf(Module.class);

        // initial printout
        printAllPersons(school);

        // evolve through TIME_DURATION time units
        for (int timeCounter = 0; timeCounter < TIME_DURATION; timeCounter++) {
            System.out.printf(
                    "################### PERIOD %d ################### %n",
                    timeCounter + 1
            );
            school.update(timeCounter);
            printAllPersons(school);
            System.out.println();
            for (Module module : allModules) {
                school.listStudentsInModule(module);
                school.listMentorsInModule(module);
            }

        }
    }

    private static void printAllPersons(School school) {
        System.out.println("All sales people in school:");
        school.getSalesPersons().forEach(person -> System.out.println(person.toString()));
        System.out.println("All mentors in school:");
        school.getMentors().forEach(mentor -> System.out.println(mentor.toString()));
        System.out.println();
        System.out.println("All students in school:");
        school.getStudents().forEach(student -> System.out.println(student.toString()));
    }
}
