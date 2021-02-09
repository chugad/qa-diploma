package ru.netology;

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
    private final SelenideElement errorPurchaseCard = $(byText("Ошибка"));

    // метод для выбора страницы

    public void clickCardPayBottom() {
        payCardBottom.click();
    }

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

    // неуспешная покупка тура (не в кредит)

    public void errorPurchase() {
        errorPurchaseCard.waitUntil(visible, 15000);
    }


    // методы описывающие ошибки при неверном заполнении полей

    public void errorCardNumberField() {
        cardNumberField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void errorMonthField() {
        monthField.parent().parent().$(".input__sub").shouldHave(exactText("Неверно указан срок действия карты"));
    }

    public void errorPastYearField() {
        yearField.parent().parent().$(".input__sub").shouldHave(exactText("Истёк срок действия карты"));
    }

    public void errorPastYearFutureField() {
        yearField.parent().parent().$(".input__sub").shouldHave(exactText("Неправильный срок действия карты")); // ошибка не предусмотрена разработчиком, текст придуман
    }

    public void errorCvcCvvField() {
        cvcCvvField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void errorOwnerField() {
        ownerField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат")); // ошибка не предусмотрена разработчиком, текст придуман
    }

    public void errorEmptyCardNumberField() {
        cardNumberField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void errorEmptyMonthField() {
        monthField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void errorEmptyYearField() {
        yearField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void errorEmptyOwnerField() {
        ownerField.parent().parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void errorEmptyCvcCvvField() {
        cvcCvvField.parent().parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }
}