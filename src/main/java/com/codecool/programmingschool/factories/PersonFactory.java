package com.codecool.programmingschool.factories;

import com.codecool.programmingschool.Mentor;
import com.codecool.programmingschool.ProgrammingLanguage;
import com.codecool.programmingschool.SalesPerson;
import com.codecool.programmingschool.Student;
import com.codecool.programmingschool.util.RandomGen;

import java.util.EnumSet;

public class PersonFactory {

    public static final int STUDENT_FEMALE_PERCENTAGE = 20;
    public static final int MENTOR_FEMALE_PERCENTAGE = 21;
    public static final int SALESPERSON_FEMALE_PERCENTAGE = 67;

    public static Student createRandomStudent() {
        int dieRoll = RandomGen.nextInt(100);
        boolean isFemale = (dieRoll < STUDENT_FEMALE_PERCENTAGE);
        String name;
        if (isFemale) {
            name = RandomGen.getRandomFemaleFullName();
        } else {
            name = RandomGen.getRandomMaleFullName();
        }
        int age = RandomGen.getRandomIntWithMeanAndStdDev(30,10);
        String birthDayAsString = RandomGen.getRandomBirthDateStringForAge(age);
        String phoneNumber = RandomGen.getRandomPhoneNumberString();

        return new Student(name, phoneNumber, birthDayAsString);
    }

    public static SalesPerson createRandomSalesPerson() {
        int dieRoll = RandomGen.nextInt(100);
        boolean isFemale = (dieRoll < SALESPERSON_FEMALE_PERCENTAGE);
        String name;
        if (isFemale) {
            name = RandomGen.getRandomFemaleFullName();
        } else {
            name = RandomGen.getRandomMaleFullName();
        }
        int age = RandomGen.getRandomIntWithMeanAndStdDev(37,15);
        String birthDayAsString = RandomGen.getRandomBirthDateStringForAge(age);
        String phoneNumber = RandomGen.getRandomPhoneNumberString();

        dieRoll = RandomGen.nextInt(100);
        if (dieRoll < 75) {
            double salary = RandomGen.getRandomIntWithMeanAndStdDev(1300, 500);
            salary = 0.9 * salary;  // 10 percent less than value
            return new SalesPerson(name, phoneNumber, birthDayAsString, salary);
        } else {
            // 25% of the time, salary is not settled before actual call to hireEmployee
            return new SalesPerson(name, phoneNumber, birthDayAsString);
        }

    }

    public static Mentor createRandomMentorWithNoLanguage() {
        int dieRoll = RandomGen.nextInt(100);
        boolean isFemale = (dieRoll < MENTOR_FEMALE_PERCENTAGE);
        String name;
        if (isFemale) {
            name = RandomGen.getRandomFemaleFullName();
        } else {
            name = RandomGen.getRandomMaleFullName();
        }
        int age = RandomGen.getRandomIntWithMeanAndStdDev(33.5,7.5);
        String birthDayAsString = RandomGen.getRandomBirthDateStringForAge(age);
        String phoneNumber = RandomGen.getRandomPhoneNumberString();
        Mentor newMentor;
        if (dieRoll < 67) {
            double salary = RandomGen.getRandomIntWithMeanAndStdDev(1300, 500);
            salary = 0.8 * salary;  // 20 percent less than value
            newMentor = new Mentor(name, phoneNumber, birthDayAsString, salary);
        } else {
            // 33% of the time, salary is not settled before actual call to hireEmployee
            newMentor = new Mentor(name, phoneNumber, birthDayAsString);
        }
        return newMentor;
    }

    public static Mentor createRandomMentorWhichKnowsLanguage(ProgrammingLanguage language) {
        Mentor newMentor = createRandomMentorWithNoLanguage();

        int nrOfKnownLanguages = RandomGen.nextInt(2) + 1;
        newMentor.learnLanguage(language);
        if (nrOfKnownLanguages == 2) {
            newMentor.learnLanguage(RandomGen.getRandomElementFromEnumSet(newMentor.getLanguagesYetToLearn()));
        }

        return newMentor;

    }

    public static Mentor createRandomFullyKnowledgeableMentor() {
        Mentor newMentor = createRandomMentorWithNoLanguage();
        EnumSet<ProgrammingLanguage> allLanguages = EnumSet.allOf(ProgrammingLanguage.class);
        for (ProgrammingLanguage language : allLanguages) {
            newMentor.learnLanguage(language);
        }

        return newMentor;
    }


}
