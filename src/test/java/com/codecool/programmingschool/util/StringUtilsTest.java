package com.codecool.programmingschool.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void Should_ReturnTrue_WhenCheckingIfIsPhoneNumberLikeWithRealWorldNumber() {
        //arrange
        String realWorldNumber = "+40 (021) 555 55 00";
        String realWorldNumber2 = "40-21-555 55 00";
        //act+assert
        assertTrue(StringUtils.isPhoneNumberLike(realWorldNumber));
        assertTrue(StringUtils.isPhoneNumberLike(realWorldNumber2));

    }

    @Test
    void Should_ReturnTrue_WhenCheckingIfIsPhoneNumberLikeWithDoubleZeroPrefix() {
        //arrange
        String testNumber = "0040 21 555 55 99";
        //act+assert
        assertTrue(StringUtils.isPhoneNumberLike(testNumber));
    }



    @Test
    void Should_ReturnSpecificString_WhenStrippingNonNumericCharactersFromExampleString() {
        //arrange
        String exampleString = "AdsbfvFGIUW4129423978aa";
        //act
        String strippedString = StringUtils.stripNonNumericCharacters(exampleString);
        //assert
        assertEquals("4129423978",strippedString);

    }
}