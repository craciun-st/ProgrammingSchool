package com.codecool.programmingschool.util;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Random;

public class RandomGen {

    public static int getRandomIntWithMeanAndStdDev(double mean, double stdDev) {
        Random randomizer = new Random();
        double normalizedRand = randomizer.nextDouble()+randomizer.nextDouble()+randomizer.nextDouble() - 1.5;
        double normalDistributedDouble = normalizedRand*stdDev*2 + mean;
        return (int) Math.rint(normalDistributedDouble);    // rounding to nearest integer
    }

    public static <E extends Enum<E>> E getRandomElementFromEnumSet(EnumSet<E> someSet) {
        Random randomizer = new Random();
        int setSize = someSet.size();
        int randomIndex = randomizer.nextInt(setSize);
        int counter = 0;
        for (E element : someSet) {
            if (counter == randomIndex) {
                return element;
            }
            counter++;
        }

        // otherwise, we get here when we have a set of size 0
        return null;
    }

    public static int nextInt(Integer bound) {
        if (bound != null) {
            return new Random().nextInt(bound);
        } else {
            return new Random().nextInt();
        }
    }

    public static String getRandomFemaleFullName() {
        Random randomizer = new Random();
        int sizeOfFirstNames = Constants.FEMALE_FIRST_NAMES.length;
        int sizeOfLastNames = Constants.LAST_NAMES.length;
        String firstName = Constants.FEMALE_FIRST_NAMES[randomizer.nextInt(sizeOfFirstNames)];
        String lastName = Constants.LAST_NAMES[randomizer.nextInt(sizeOfLastNames)];
        return firstName + " " + lastName;
    }

    public static String getRandomMaleFullName() {
        Random randomizer = new Random();
        int sizeOfFirstNames = Constants.MALE_FIRST_NAMES.length;
        int sizeOfLastNames = Constants.LAST_NAMES.length;
        String firstName = Constants.MALE_FIRST_NAMES[randomizer.nextInt(sizeOfFirstNames)];
        String lastName = Constants.LAST_NAMES[randomizer.nextInt(sizeOfLastNames)];
        return firstName + " " + lastName;
    }

    public static String getRandomBirthDateStringForAge(int age) {
        int currentYear = LocalDate.now().getYear();
        int birthYear = currentYear - age;
        int birthMonth = RandomGen.nextInt(12) + 1;
        int birthDay = Math.max(RandomGen.getRandomIntWithMeanAndStdDev(15, 7),1);
        birthDay = Math.min(birthDay, 28);

        return String.format("%04d-%02d-%02d",birthYear, birthMonth, birthDay);
    }

    public static String getRandomPhoneNumberString() {
        boolean isMobilePhoneNumber = (RandomGen.nextInt(100) < 50);
        String prefix;
        String rest;
        if (isMobilePhoneNumber) {
            prefix = String.format("%04d",RandomGen.nextInt(500)+500);
            rest = String.format(
                    "%03d %03d",
                    RandomGen.nextInt(1000),
                    RandomGen.nextInt(1000)
            );
        }
        else {
            prefix = "(021)";
            rest = String.format(
                    "%03d %02d %02d",
                    RandomGen.nextInt(1000),
                    RandomGen.nextInt(100),
                    RandomGen.nextInt(100)
            );
        }
        return String.format("%s %s", prefix, rest);
    }
}
