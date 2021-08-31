package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DataHelper {
    private DataHelper() {}

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

    public static String getValidCardNumber() {
        String[] validCardNumbers = {"4444 4444 4444 0000", "5555 5555 5555 0000"};
        val index = faker.number().numberBetween(0, 2);
        return validCardNumbers[index];
    }

    public static String getApprovedCardNumber() {
        String[] approvedCardNumbers = {"4444 4444 4444 4441", "5555 5555 5555 5551"};
        val index = faker.number().numberBetween(0, 2);
        return approvedCardNumbers[index];
    }

    public static String getValidMonth() {
        val validMonth = LocalDate.now().plusMonths(faker.number().numberBetween(1, 12));
        return validMonth.format(monthFormatter);
    }

    public static String getValidYear() {
        val validYear = LocalDate.now().plusYears(faker.number().numberBetween(1, 4));
        return validYear.format(yearFormatter);
    }

    public static String getValidHolder() {
        return faker.name().name();
    }

    public static String getValidCVC() {
        return faker.number().digits(3) ;
    }

    public static String getInvalidCardNumber() {
        String[] invalidCardNumbers = {"0000 0000 4444 4488", "5555 5555 5555 444"};
        val index = faker.number().numberBetween(0, 2);
        return invalidCardNumbers[index];
    }

    public static String getDeclinedCardNumber() {
        String[] declinedCardNumbers = {"4444 4444 4444 4442", "5555 5555 5555 5552"};
        val index = faker.number().numberBetween(0, 2);
        return declinedCardNumbers[index];
    }

    public static String getInvalidMonth1Digit() {
        return String.valueOf(faker.number().numberBetween(0,10));
    }

    public static String getInvalidMonth2Digits() {
        return String.valueOf(faker.number().numberBetween(13,100));
    }

    public static String getInvalidYear1Digit() {
        return faker.number().digits(1);
    }

    public static String getInvalidYear2Digits() {
        val InvalidYear = LocalDate.now().minusYears(faker.number().numberBetween(1, 10));
        return InvalidYear.format(yearFormatter);
    }

    public static String getInvalidHolder() {
        return faker.internet().password(8, 20, true, true, true);
    }

    public static String getInvalidCVC() {
        return faker.number().digits(2) ;
    }

    public static CardInfo getValidCardInfo(){
        val validCardInfo = new CardInfo();
        validCardInfo.cardNumber = getValidCardNumber();
        validCardInfo.month = getValidMonth();
        validCardInfo.year = getValidYear();
        validCardInfo.holder = getValidHolder();
        validCardInfo.cvc_cvv = getValidCVC();
        return validCardInfo;
    }

    public static CardInfo getInvalidCardInfo(){
        val invalidCardInfo = new CardInfo();
        invalidCardInfo.cardNumber = getInvalidCardNumber();
        invalidCardInfo.month = getInvalidMonth2Digits();
        invalidCardInfo.year = getInvalidYear2Digits();
        invalidCardInfo.holder = getInvalidHolder();
        invalidCardInfo.cvc_cvv = getInvalidCVC();
        return invalidCardInfo;
    }

    public static CardInfo getApprovedValidCardInfo(){
        val approvedValidCardInfo = getValidCardInfo();
        approvedValidCardInfo.cardNumber = getApprovedCardNumber();
        return approvedValidCardInfo;
    }

    public static CardInfo getDeclinedValidCardInfo(){
        val declinedValidCardInfo = getValidCardInfo();
        declinedValidCardInfo.cardNumber = getDeclinedCardNumber();
        return declinedValidCardInfo;
    }

    public static CardInfo getInvalidCardNumberInCardInfo(){
        val invalidCardNumberInCardInfo = getValidCardInfo();
        invalidCardNumberInCardInfo.cardNumber = getInvalidCardNumber();
        return invalidCardNumberInCardInfo;
    }

    public static CardInfo getInvalidMonth1DigitInCardInfo(){
        val invalidMonthInCardInfo = getValidCardInfo();
        invalidMonthInCardInfo.month = getInvalidMonth1Digit();
        return invalidMonthInCardInfo;
    }

    public static CardInfo getInvalidMonth2DigitsInCardInfo(){
        val invalidMonthInCardInfo = getValidCardInfo();
        invalidMonthInCardInfo.month = getInvalidMonth2Digits();
        return invalidMonthInCardInfo;
    }

    public static CardInfo getInvalidYear1DigitInCardInfo(){
        val invalidYearInCardInfo = getValidCardInfo();
        invalidYearInCardInfo.year = getInvalidYear1Digit();
        return invalidYearInCardInfo;
    }

    public static CardInfo getInvalidYear2DigitsInCardInfo(){
        val invalidYearInCardInfo = getValidCardInfo();
        invalidYearInCardInfo.year = getInvalidYear2Digits();
        return invalidYearInCardInfo;
    }

    public static CardInfo getInvalidHolderInCardInfo(){
        val invalidHolderInCardInfo = getValidCardInfo();
        invalidHolderInCardInfo.holder = getInvalidHolder();
        return invalidHolderInCardInfo;
    }

    public static CardInfo getInvalidCVCInCardInfo(){
        val invalidCVCInCardInfo = getValidCardInfo();
        invalidCVCInCardInfo.cvc_cvv = getInvalidCVC();
        return invalidCVCInCardInfo;
    }
}
