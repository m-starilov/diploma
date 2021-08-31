package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPageFieldsTest {
    private static MainPage mainPage;

    @BeforeEach
    void openMainPage() {
        mainPage = open("http://localhost:8080", MainPage.class);
    }

    @Test
    void inputInvalidCardNumberForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidCardNumberInCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidMonth1DigitForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidMonth1DigitInCardInfo());
        mainPage.monthWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidMonth2DigitsForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidMonth2DigitsInCardInfo());
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidYear1DigitForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidYear1DigitInCardInfo());
        mainPage.yearWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidYear2DigitsForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidYear2DigitsInCardInfo());
        mainPage.yearExpiredMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidHolderForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidHolderInCardInfo());
        mainPage.holderWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidCvcForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidCVCInCardInfo());
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputAndDeleteCardInfoForCardPayment() {
        mainPage.inputAndDeleteCardInfo(DataHelper.getInvalidCardInfo());
        mainPage.fieldsShouldBeEmpty();
    }

    @Test
    void inputInvalidCardNumberForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidCardNumberInCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidMonth1DigitForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidMonth1DigitInCardInfo());
        mainPage.monthWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidMonth2DigitsForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidMonth2DigitsInCardInfo());
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidYear1DigitForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidYear1DigitInCardInfo());
        mainPage.yearWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidYear2DigitsForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidYear2DigitsInCardInfo());
        mainPage.yearExpiredMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidHolderForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidHolderInCardInfo());
        mainPage.holderWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidCvcForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getInvalidCVCInCardInfo());
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputAndDeleteCardInfoForCreditPayment() {
        mainPage.inputAndDeleteCardInfo(DataHelper.getInvalidCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
    }

}
