package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Page {

    //кнопки

    private final SelenideElement payCardBottom = $$("button").find(exactText("Купить"));
    private final SelenideElement payCreditCardBottom = $$("button").find(exactText("Купить в кредит"));
    private final SelenideElement continueBottom = $$("button").find(exactText("Продолжить"));

    //поля

    private final SelenideElement cardNumberField = $$(".input__control").get(0);
    private final SelenideElement monthField = $$(".input__control").get(1);
    private final SelenideElement yearField = $$(".input__control").get(2);
    private final SelenideElement ownerField = $$(".input__control").get(3);
    private final SelenideElement cvcCvvField = $$(".input__control").get(4);

    // успешная покупка

    private final SelenideElement happyPurchaseCard = $(byText("Операция одобрена Банком."));

    // метод для выбора страницы с покупкой карты

    public void clickCardPayBottom() {
        payCardBottom.click();
    }

    // метод для выбора страницы с покупкой в кредит

    public void clickCreditCardPayBottom() {
        payCreditCardBottom.click();
    }

    // нажать на кнопку продолжить

    public void clickContinueBottom() {
        continueBottom.click();
    }

    // метод для заполнения всех полей формы

    public void insertCardData(Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getOwner());
        cvcCvvField.setValue(card.getCvcCvv());
    }

    // успешная покупка тура (не в кредит)

    public void happyPurchase() {
        happyPurchaseCard.waitUntil(visible, 15000);
    }

}