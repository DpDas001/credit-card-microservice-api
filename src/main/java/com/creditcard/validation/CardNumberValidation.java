package com.creditcard.validation;

import org.springframework.stereotype.Component;

@Component
public class CardNumberValidation {

    public boolean isValid(String input) {
        char[] chars = removeInvalidCharacters(input);
        return getSum(chars) % 10 == 0;
    }

    /*
    Remove non integer characters using regex check
    example 4596-0690-9685-2253 is converted into 4596069096852253
     */
    private char[] removeInvalidCharacters(String input) {
        String creditCardNumber = input.replaceAll("[^\\d]", "");
        return creditCardNumber.toCharArray();
    }
    /*         Step 1 (getInReverseOrder): Read characters in reverse order  4003600000000014 <<<<<< from right to left
               Step 2 (getElementValue): get numbers which are in even index and replace them with double the value
                           X -> 2 * X
               Step 3 (getSum): then add all numbers
               Step 4 :
                        Step4a: valid number  -> if Sum % 10 == 0
                        Step4b: invalid number-> if Sum % 10 != 0
            */
    private int getSum(char[] chars) {
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            int number = getInReverseOrder(chars, i);
            sum += getElementValue(i, number);
        }
        return sum;
    }
    /*
    Read characters in reverse order  4596069096852253 <<<<<< from right to left
     */
    private int getInReverseOrder(char[] chars, int i) {
        int indexInReverseOrder = chars.length - 1 - i;
        char character = chars[indexInReverseOrder];
        return Character.getNumericValue(character);
    }
    /*
     finding even number index characters
     */
    private int getElementValue(int i, int number) {
        if (i % 2 != 0) {
            return getOddElementValue(number);
        } else {
            return number;
        }
    }
    /*
        even number index characters are doubled
         */
    private int getOddElementValue(int element) {
        int value = element * 2;
        if (value <= 9) {
            return value;
        }
        return value - 9;
    }
}
