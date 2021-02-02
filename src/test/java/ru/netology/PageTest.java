package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


class PageTest {

    @BeforeEach
    void shouldStartBeforeEachTest() {
        open("http://localhost:8080");
    }

    @Test
    // 1. Успешная покупка тура с валидным номером карты с разрешенной оплатой (APPROVED).
    public void happyPathCard() {
        Card approvedCard = new Card();
        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
    }
}