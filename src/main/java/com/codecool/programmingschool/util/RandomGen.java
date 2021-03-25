package com.codecool.programmingschool.util;

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
}
