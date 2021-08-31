package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DBHelper;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private static MainPage mainPage;

    @BeforeEach
    void openMainPage(){
        mainPage = open("http://localhost:8080", MainPage.class);
    }

    @Test
    void inputEmptyDataForCardPayment(){
        mainPage.paymentByCard(new DataHelper.CardInfo());
        mainPage.fieldsShouldBeEmpty();
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongFormatMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderRequiredFieldMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidCardInfoForCardPayment(){
        mainPage.paymentByCard(DataHelper.getInvalidCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderWrongFormatMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputValidDataForCardPayment(){
        mainPage.paymentByCard(DataHelper.getValidCardInfo());
        mainPage.declinedMessageShouldBeVisible();
    }

    @Test
    void inputApprovedValidDataForCardPayment(){
        mainPage.paymentByCard(DataHelper.getApprovedValidCardInfo());
        mainPage.approvedMessageShouldBeVisible();
        assertEquals("APPROVED", DBHelper.getPaymentStatus());
    }

    @Test
    void inputDeclinedValidDataForCardPayment(){
        mainPage.paymentByCard(DataHelper.getDeclinedValidCardInfo());
        mainPage.declinedMessageShouldBeVisible();
        assertEquals("DECLINED", DBHelper.getPaymentStatus());
    }

    @Test
    void inputEmptyDataForCreditPayment(){
        mainPage.paymentByCredit(new DataHelper.CardInfo());
        mainPage.fieldsShouldBeEmpty();
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongFormatMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderRequiredFieldMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputInvalidCardInfoForCreditPayment(){
        mainPage.paymentByCredit(DataHelper.getInvalidCardInfo());
        mainPage.cardNumberWrongFormatMessagesShouldBeVisible();
        mainPage.monthWrongDateErrorMessagesShouldBeVisible();
        mainPage.yearWrongFormatMessagesShouldBeVisible();
        mainPage.holderWrongFormatMessagesShouldBeVisible();
        mainPage.cvcWrongFormatMessagesShouldBeVisible();
    }

    @Test
    void inputValidDataForCreditPayment(){
        mainPage.paymentByCredit(DataHelper.getValidCardInfo());
        mainPage.declinedMessageShouldBeVisible();
    }

    @Test
    void inputApprovedValidDataForCreditPayment(){
        mainPage.paymentByCredit(DataHelper.getApprovedValidCardInfo());
        mainPage.approvedMessageShouldBeVisible();
        assertEquals("APPROVED", DBHelper.getCreditStatus());
    }

    @Test
    void inputDeclinedValidDataForCreditPayment(){
        mainPage.paymentByCredit(DataHelper.getDeclinedValidCardInfo());
        mainPage.declinedMessageShouldBeVisible();
        assertEquals("DECLINED", DBHelper.getCreditStatus());
    }
}

