package com.creditcard.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardNumberValidationTest {

    private CardNumberValidation cardNumberValidation;

    @BeforeEach
    void setUp() {
        cardNumberValidation = new CardNumberValidation();
    }

    @Test
    void shouldReturnTrueForValidNumbers() {
        assertAll(
                () -> assertTrue(cardNumberValidation.isValid("5277 0891 2077 3260")),
                () -> assertTrue(cardNumberValidation.isValid("4596-0690-9685-2253")),
                () -> assertTrue(cardNumberValidation.isValid("4852789106979280262"))
        );
    }

    @Test
    void shouldReturnFalseForInvalidNumbers() {
        assertAll(
                () -> assertFalse(cardNumberValidation.isValid("4862 7891 0697 922 0251")),
                () -> assertFalse(cardNumberValidation.isValid("3143-6933-8731-4539")),
                () -> assertFalse(cardNumberValidation.isValid("6259310784561726"))
        );
    }
}