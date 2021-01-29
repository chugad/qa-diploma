package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class PageTest {

    String approveCardNum = DataGenerator.approveCardNumber();
    String declineCardNum = DataGenerator.declineCardNumber();
    String invalidCardNum = DataGenerator.invalidCardNumber();
    String randomCardNum = DataGenerator.randomCardNumber();
    String nowM = DataGenerator.nowMonth();
    String randomM = DataGenerator.randomMonth();
    String nowY = DataGenerator.nowYear();
    String pastY = DataGenerator.pastYear();
    String futureInvalidY = DataGenerator.futureInvalidYear();
    String futureValidY = DataGenerator.futureValidYear();
    String validCardOwn = DataGenerator.validCardOwner();
    String invalidCardOwn = DataGenerator.invalidCardOwner();
    String validCvc = DataGenerator.validCvcCvv();
    String invalidCvc = DataGenerator.invalidCvcCvv();

    @BeforeEach
    void shouldStartBeforeEachTest() {
        open("http://localhost:8080");

    }

    @Test
    // 1. Успешная покупка тура с валидным номером карты с разрешенной оплатой (APPROVED).
    public void happyPathCard() {
        $(byText("Купить")).waitUntil(text("Купить"), 4000).shouldBe(visible).click();
        $(byText("Номер карты")).setValue(approveCardNum);
        $(byText("Месяц")).setValue(nowM);
        $(byText("Год")).setValue(nowY);
        $(byText("Владелец")).setValue(validCardOwn);
        $(byText("CVC/CVV")).setValue(validCvc);
        $(".notification__content").waitUntil(text("Операция одобрена Банком."), 15000).shouldBe(visible);
    }
}