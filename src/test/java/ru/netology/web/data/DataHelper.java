package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {
    }

    public static Faker faker = new Faker();
    public static DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    public static DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String cvc_cvv;
    }

    public static String getApprovedCardNumber() {
        String[] approvedCardNumbers = {"4444 4444 4444 4441", "5555 5555 5555 5551"};
        val index = faker.number().numberBetween(0, 2);
        return approvedCardNumbers[index];
    }

    public static String getDeclinedCardNumber() {
        String[] declinedCardNumbers = {"4444 4444 4444 4442", "5555 5555 5555 5552"};
        val index = faker.number().numberBetween(0, 2);
        return declinedCardNumbers[index];
    }

    public static CardInfo getValidCardInfo() {
        val validCardInfo = new CardInfo();
        validCardInfo.cardNumber = "4444 4444 4444 0000";
        validCardInfo.month = LocalDate.now().plusMonths(faker.number().numberBetween(1, 12)).format(monthFormatter);
        validCardInfo.year = LocalDate.now().plusYears(faker.number().numberBetween(1, 4)).format(yearFormatter);
        validCardInfo.holder = faker.name().name();
        validCardInfo.cvc_cvv = faker.number().digits(3);
        return validCardInfo;
    }

    public static CardInfo getInvalidCardInfo() {
        val invalidCardInfo = new CardInfo();
        invalidCardInfo.cardNumber = "5555 5555 5555 444";
        invalidCardInfo.month = String.valueOf(faker.number().numberBetween(13, 100));
        invalidCardInfo.year = LocalDate.now().minusYears(faker.number().numberBetween(1, 10)).format(yearFormatter);
        invalidCardInfo.holder = faker.internet()
                .password(8, 20, true, true, true);
        invalidCardInfo.cvc_cvv = faker.number().digits(2);
        return invalidCardInfo;
    }

    public static CardInfo getApprovedCardInfo() {
        val approvedValidCardInfo = getValidCardInfo();
        approvedValidCardInfo.cardNumber = getApprovedCardNumber();
        return approvedValidCardInfo;
    }

    public static CardInfo getDeclinedCardInfo() {
        val declinedValidCardInfo = getValidCardInfo();
        declinedValidCardInfo.cardNumber = getDeclinedCardNumber();
        return declinedValidCardInfo;
    }

    public static CardInfo getInvalidCardNumberInCardInfo() {
        val invalidCardNumberInCardInfo = getValidCardInfo();
        invalidCardNumberInCardInfo.cardNumber = "0000 0000 4444 4488";
        return invalidCardNumberInCardInfo;
    }

    public static CardInfo getInvalidShortCardNumberInCardInfo() {
        val invalidCardNumberInCardInfo = getValidCardInfo();
        invalidCardNumberInCardInfo.cardNumber = "5555 5555 5555 444";
        return invalidCardNumberInCardInfo;
    }

    public static CardInfo getInvalidMonth1DigitInCardInfo() {
        val invalidMonthInCardInfo = getValidCardInfo();
        invalidMonthInCardInfo.month = String.valueOf(faker.number().numberBetween(0, 10));
        return invalidMonthInCardInfo;
    }

    public static CardInfo getInvalidMonth2DigitsInCardInfo() {
        val invalidMonthInCardInfo = getValidCardInfo();
        invalidMonthInCardInfo.month = String.valueOf(faker.number().numberBetween(13, 100));
        return invalidMonthInCardInfo;
    }

    public static CardInfo getInvalidYear1DigitInCardInfo() {
        val invalidYearInCardInfo = getValidCardInfo();
        invalidYearInCardInfo.year = faker.number().digits(1);
        return invalidYearInCardInfo;
    }

    public static CardInfo getInvalidYear2DigitsInCardInfo() {
        val invalidYearInCardInfo = getValidCardInfo();
        invalidYearInCardInfo.year = LocalDate.now().minusYears(faker.number().numberBetween(1, 10))
                .format(yearFormatter);
        return invalidYearInCardInfo;
    }

    public static CardInfo getInvalidHolderInCardInfo() {
        val invalidHolderInCardInfo = getValidCardInfo();
        invalidHolderInCardInfo.holder = faker.internet()
                .password(8, 20, true, true, true);
        return invalidHolderInCardInfo;
    }

    public static CardInfo getInvalidCVCInCardInfo() {
        val invalidCVCInCardInfo = getValidCardInfo();
        invalidCVCInCardInfo.cvc_cvv = faker.number().digits(2);
        return invalidCVCInCardInfo;
    }
}