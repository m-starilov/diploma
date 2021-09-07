package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
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
    void openMainPage() {
        Configuration.startMaximized = true;
        mainPage = open("http://localhost:8080", MainPage.class);
    }

    @Test
    void inputApprovedDataForCardPayment() {
        mainPage.paymentByCard(DataHelper.getApprovedCardInfo());
        mainPage.approvedMessageShouldBeVisible();
        assertEquals("APPROVED", DBHelper.getPaymentStatus());
    }

    @Test
    void inputDeclinedDataForCardPayment() {
        mainPage.paymentByCard(DataHelper.getDeclinedCardInfo());
        mainPage.declinedMessageShouldBeVisible();
        assertEquals("DECLINED", DBHelper.getPaymentStatus());
    }

    @Test
    void inputApprovedDataForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getApprovedCardInfo());
        mainPage.approvedMessageShouldBeVisible();
        assertEquals("APPROVED", DBHelper.getCreditStatus());
    }

    @Test
    void inputDeclinedDataForCreditPayment() {
        mainPage.paymentByCredit(DataHelper.getDeclinedCardInfo());
        mainPage.declinedMessageShouldBeVisible();
        assertEquals("DECLINED", DBHelper.getCreditStatus());
    }
}