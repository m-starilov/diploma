package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MainPageFieldsTest {
    private static MainPage mainPage;

    @BeforeEach
    void openMainPage() {
        Configuration.startMaximized = true;
        mainPage = open("http://localhost:8080", MainPage.class);
    }

    @Test
    void inputInvalidCardNumberForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidCardNumberInCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidShortCardNumberForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidShortCardNumberInCardInfo());
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
    void inputEmptyDataForCardPayment() {
        mainPage.paymentByCard(new DataHelper.CardInfo());
        mainPage.fieldsShouldBeEmpty();
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongFormatMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderRequiredFieldMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidCardInfoForCardPayment() {
        mainPage.paymentByCard(DataHelper.getInvalidCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderWrongFormatMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputAndDeleteCardInfoForCardPayment() {
        mainPage.inputAndDeleteCardInfo(DataHelper.getInvalidCardInfo());
        mainPage.fieldsShouldBeEmpty();
    }
}
