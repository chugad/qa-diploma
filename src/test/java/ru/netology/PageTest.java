package ru.netology;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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
        $$("button").find(visible.exactText("Купить в кредит")).click();
        $$(".input__control").get(0).setValue(approveCardNum);
        $$(".input__control").get(1).setValue(nowM);
        $$(".input__control").get(2).setValue(nowY);
        $$(".input__control").get(3).setValue(validCardOwn);
        $$(".input__control").get(4).setValue("999");
        $$("button").find(visible.exactText("Продолжить")).click();
        $(byText("Операция одобрена Банком.")).waitUntil(visible, 10000);
    }
}