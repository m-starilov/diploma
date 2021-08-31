package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement buyButton = $$("button").findBy(text("Купить"));
    private final SelenideElement buyInCreditButton = $$("button").findBy(text("Купить в кредит"));
    private final SelenideElement nextButton = $$("button").findBy(text("Продолжить"));
    private final SelenideElement buyHeading = $(byText("Оплата по карте"));
    private final SelenideElement buyInCreditHeading = $(byText("Кредит по данным карты"));
    private final SelenideElement cardNumberField = $$(".input").findBy(text("Номер карты"));
    private final SelenideElement cardNumberInput = cardNumberField.$("input");
    private final SelenideElement monthField = $$(".input").findBy(text("Месяц"));
    private final SelenideElement monthInput = monthField.$("input");
    private final SelenideElement yearField = $$(".input").findBy(text("Год"));
    private final SelenideElement yearInput = yearField.$("input");
    private final SelenideElement holderField = $$(".input").findBy(text("Владелец"));
    private final SelenideElement holderInput = holderField.$("input");
    private final SelenideElement cvcField = $$(".input").findBy(text("CVC/CVV"));
    private final SelenideElement cvcInput = cvcField.$("input");
    private final SelenideElement approvedMessage = $(byText("Операция одобрена Банком."));
    private final SelenideElement declinedMessage = $(byText("Ошибка! Банк отказал в проведении операции."));
    private static final String wrongFormatMessage = "Неверный формат";
    private static final String requiredFieldMessage = "Поле обязательно для заполнения";
    private static final String wrongDateMessage = "Неверно указан срок действия карты";
    private static final String expiredDateMessage = "Истёк срок действия карты";
    private final String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    public void buyHeadingShouldBeVisible(){
        buyHeading.shouldBe(visible);
    }

    public void buyInCreditHeadingShouldBeVisible(){
        buyInCreditHeading.shouldBe(visible);
    }

    public void approvedMessageShouldBeVisible(){
        approvedMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void declinedMessageShouldBeVisible(){
        declinedMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void errorMessagesShouldBeVisible(SelenideElement field, String text){
        field.$(".input__sub").shouldHave(exactText(text)).shouldBe(visible);
    }

    public void cardNumberWrongFormatMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(cardNumberField, wrongFormatMessage);
    }

    public void monthWrongFormatMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(monthField, wrongFormatMessage);
    }

    public void monthWrongDateErrorMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(monthField, wrongDateMessage);
    }

    public void yearWrongFormatMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(yearField, wrongFormatMessage);
    }

    public void yearExpiredMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(yearField, expiredDateMessage);
    }

    public void holderRequiredFieldMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(holderField, requiredFieldMessage);
    }

    public void holderWrongFormatMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(holderField, wrongFormatMessage);
    }

    public void cvcWrongFormatMessagesShouldBeVisible(){
        errorMessagesShouldBeVisible(cvcField, wrongFormatMessage);
    }

    public void fieldsShouldBeEmpty(){
        cardNumberInput.shouldBe(empty);
        monthInput.shouldBe(empty);
        yearInput.shouldBe(empty);
        holderInput.shouldBe(empty);
        cvcInput.shouldBe(empty);
    }

    public void setCardInfoValues(DataHelper.CardInfo cardInfo){
        cardNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(cardInfo.getMonth());
        yearInput.setValue(cardInfo.getYear());
        holderInput.setValue(cardInfo.getHolder());
        cvcInput.setValue(cardInfo.getCvc_cvv());
    }

    public void deleteCardInfoValues(){
        cardNumberInput.setValue(deleteString);
        monthInput.setValue(deleteString);
        yearInput.setValue(deleteString);
        holderInput.setValue(deleteString);
        cvcInput.setValue(deleteString);
    }

    public void paymentByCard(DataHelper.CardInfo cardInfo){
        buyButton.click();
        buyHeadingShouldBeVisible();
        setCardInfoValues(cardInfo);
        nextButton.click();
    }

    public void paymentByCredit(DataHelper.CardInfo cardInfo){
        buyInCreditButton.click();
        buyInCreditHeadingShouldBeVisible();
        setCardInfoValues(cardInfo);
        nextButton.click();
    }

    public void inputAndDeleteCardInfo(DataHelper.CardInfo cardInfo){
        buyButton.click();
        buyHeadingShouldBeVisible();
        setCardInfoValues(cardInfo);
        deleteCardInfoValues();
    }
}
