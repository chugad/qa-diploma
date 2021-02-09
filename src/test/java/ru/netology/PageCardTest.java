package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


class PageCardTest {

    @BeforeEach
    void shouldStartBeforeEachTest() {
        open("http://localhost:8080");
    }

    @Test
    // 1. Успешная покупка тура с валидным номером карты с разрешенной оплатой (APPROVED).
    public void happyPath_validAllFields_approvedCard() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
    }

    @Test
    // 2. Успешная покупка тура – месяц и год равны текущим.
    public void happyPath_validAllFields_approvedCard_now() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());
        approvedCard.setMonth(CardDataGenerator.nowMonth());
        approvedCard.setYear(CardDataGenerator.nowYear());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
    }

//    @Test // необходимо оформить ишью, так как ФР не соответствует ОР
//    // 3. Неуспешная покупка тура с валидным номером карты с запрещенной оплатой (DECLINED).
//    public void errorPath_validAllFields_declinedCard() {
//        Card declinedCard = new Card();
//        declinedCard.setCardNumber(CardDataGenerator.declineCardNumber());
//
//        Page mainPage = new Page();
//        mainPage.clickCardPayBottom();
//        mainPage.insertCardData(declinedCard);
//        mainPage.clickContinueBottom();
//        mainPage.errorPurchase();
//    }

    @Test
    // 4. Неуспешная покупка тура с пустым полем "Номер карты".
    public void errorPath_emptyCardNumber_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber("");

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyCardNumberField();
    }

    @Test
    // 5. Неуспешная покупка тура с пустым полем "Месяц".
    public void errorPath_emptyMonth_validAnotherFields() {
        Card newCard = new Card();
        newCard.setMonth("");

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyMonthField();
    }

    @Test
    // 6. Неуспешная покупка тура с пустым полем "Год".
    public void errorPath_emptyYear_validAnotherFields() {
        Card newCard = new Card();
        newCard.setYear("");

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyYearField();
    }

    @Test
    // 7. Неуспешная покупка тура с пустым полем "Владелец".
    public void errorPath_emptyOwner_validAnotherFields() {
        Card newCard = new Card();
        newCard.setOwner("");

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyOwnerField();
    }

    @Test
    // 8. Неуспешная покупка тура с пустым полем "CVC/CVV".
    public void errorPath_emptyCvcCvv_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCvcCvv("");

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyCvcCvvField();
    }

    @Test
    // 9. Неуспешная покупка тура с не валидным номером карты (15 цифр).
    public void errorPath_invalidCardNumber15_validAnotherFields15() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber15());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCardNumberField();
    }

    @Test
    // 10. Неуспешная покупка тура с не валидным номером карты (1 цифрa).
    public void errorPath_invalidCardNumber1_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber1());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCardNumberField();
    }

    @Test
    // 11. Неуспешная покупка тура с не валидным номером карты. (16 цифр)
    public void errorPath_invalidCardNumber_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPurchase();
    }

    @Test
    // 12. Неуспешная покупка тура – месяц и год меньше текущих.
    public void errorPath_invalidPastYear_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setYear(CardDataGenerator.invalidYearPast());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPastYearField();
    }

//    @Test // необходимо оформить ишью, так как ФР не соответствует ОР
//    // 13. Неуспешная покупка тура – месяц и год больше текущих на 5 и более лет.
//    public void errorPath_invalidFutureYear_validAnotherFields() {
//        Card newCard = new Card();
//        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
//        newCard.setYear(CardDataGenerator.invalidYearFuture());
//
//        Page mainPage = new Page();
//        mainPage.clickCardPayBottom();
//        mainPage.insertCardData(newCard);
//        mainPage.clickContinueBottom();
//        mainPage.errorPastYearFutureField();
//    }

    @Test
    // 12.1 Неуспешная покупка тура – месяц не валидный.
    public void errorPath_invalidMonth_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setMonth(CardDataGenerator.invalidMonth());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorMonthField();
    }

//    @Test // необходимо оформить ишью, так как ФР не соответствует ОР
//    // 13 Неуспешная покупка – цифры и нерелевантные символы
//    public void errorPath_invalidCardOwner_validAnotherFields() {
//        Card newCard = new Card();
//        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
//        newCard.setOwner(CardDataGenerator.invalidCardOwner());
//
//        Page mainPage = new Page();
//        mainPage.clickCardPayBottom();
//        mainPage.insertCardData(newCard);
//        mainPage.clickContinueBottom();
//        mainPage.errorOwnerField();
//    }
//
//    @Test // необходимо оформить ишью, так как ФР не соответствует ОР
//    // 14. Неуспешная покупка – буквы русского алфавита
//    public void errorPath_invalidCardOwnerRusLanguage_validAnotherFields() {
//        Card newCard = new Card();
//        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
//        newCard.setOwner(CardDataGenerator.invalidCardOwnerRusLanguage());
//
//        Page mainPage = new Page();
//        mainPage.clickCardPayBottom();
//        mainPage.insertCardData(newCard);
//        mainPage.clickContinueBottom();
//        mainPage.errorOwnerField();
//    }

    @Test
    // 19. Неуспешная покупка – не валидный код (2 цифры).
    public void errorPath_invalidCvcCvv_validAnotherFields() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setCvcCvv(CardDataGenerator.invalidCvcCvv());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCvcCvvField();
    }

}