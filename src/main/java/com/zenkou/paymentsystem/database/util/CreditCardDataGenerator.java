package com.zenkou.paymentsystem.database.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCardDataGenerator {

    private static final Random RANDOM = new Random();
    private static final int MIN_CVV_VALUE = 100;
    private static final int MAX_CVV_VALUE = 999;
    private static final int MAX_NUMBER_VALUE = 9999;
    private static final int MIN_NUMBER_VALUE = 1000;
    private static final int LIMIT_OPERATION = 3;
    private static final int MAX_NUMBER_LENGTH = 4;

    public static int generatePinCode() {
        return generateRandomNumbers();
    }

    public static int generateCvv() {
        return MIN_CVV_VALUE + RANDOM.nextInt(MAX_CVV_VALUE - MIN_CVV_VALUE);
    }

    @SneakyThrows
    public static String generateCreditCardNumber() {
        String creditCardNumber = "";
        for (int i = 0; i < MAX_NUMBER_LENGTH; i++) {
            creditCardNumber = i != LIMIT_OPERATION ? creditCardNumber + generateRandomNumbers() + " " : creditCardNumber + generateRandomNumbers();
        }
        return creditCardNumber;
    }

    private static int generateRandomNumbers() {
        return MIN_NUMBER_VALUE + RANDOM.nextInt(MAX_NUMBER_VALUE - MIN_NUMBER_VALUE);
    }
}
