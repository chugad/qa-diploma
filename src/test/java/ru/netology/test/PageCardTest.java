package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Card;
import ru.netology.data.CardDataGenerator;
import ru.netology.data.SQLDataGenerator;
import ru.netology.page.Page;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;


class PageCardTest {

    @BeforeEach
    void shouldStartBeforeEachTest() {
        open("http://localhost:8080");
    }

    // ПОКУПКА ТУРА НЕ В КРЕДИТ, КНОПКА "КУПИТЬ"

    @Test
    // 1. Успешная покупка тура с валидным номером карты с разрешенной оплатой (APPROVED).
    public void happyPath_validAllFields_approvedCard_CASH() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
        Assertions.assertTrue(SQLDataGenerator.approvedPay());
    }

    @Test
    // 2. Успешная покупка тура – месяц и год равны текущим.
    public void happyPath_validAllFields_approvedCard_now_CASH() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());
        approvedCard.setMonth(CardDataGenerator.nowMonth());
        approvedCard.setYear(CardDataGenerator.nowYear());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
        Assertions.assertTrue(SQLDataGenerator.approvedPay());
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/2
    // 3. Неуспешная покупка тура с валидным номером карты с запрещенной оплатой (DECLINED).
    public void errorPath_validAllFields_declinedCard_CASH() {
        Card declinedCard = new Card();
        declinedCard.setCardNumber(CardDataGenerator.declineCardNumber());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(declinedCard);
        mainPage.clickContinueBottom();
        mainPage.errorPurchase();
        Assertions.assertTrue(SQLDataGenerator.declinedPay());
    }

    @Test
    // 4. Неуспешная покупка тура с пустым полем "Номер карты".
    public void errorPath_emptyCardNumber_validAnotherFields_CASH() {
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
    public void errorPath_emptyMonth_validAnotherFields_CASH() {
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
    public void errorPath_emptyYear_validAnotherFields_CASH() {
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
    public void errorPath_emptyOwner_validAnotherFields_CASH() {
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
    public void errorPath_emptyCvcCvv_validAnotherFields_CASH() {
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
    public void errorPath_invalidCardNumber15_validAnotherFields15_CASH() {
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
    public void errorPath_invalidCardNumber1_validAnotherFields_CASH() {
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
    public void errorPath_invalidCardNumber_validAnotherFields_CASH() {
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
    public void errorPath_invalidPastYear_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setYear(CardDataGenerator.invalidYearPast());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPastYearField();
    }

    @Test
    // 13. Неуспешная покупка тура – месяц и год больше текущих на 6 и более лет.
    public void errorPath_invalidFutureYear_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setYear(CardDataGenerator.invalidYearFuture());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPastYearFutureField();
    }

    @Test
    // 14. Неуспешная покупка тура – не валидный месяц.
    public void errorPath_invalidMonth_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setMonth(CardDataGenerator.invalidMonth());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorMonthField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/3
    // 15. Неуспешная покупка тура – нулевое значение месяца "00".
    public void errorPath_invalidNullMonth_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setMonth(CardDataGenerator.nullMonth());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorMonthField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/4
    // 16. Неуспешная покупка тура – цифры и нерелевантные символы
    public void errorPath_invalidCardOwner_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setOwner(CardDataGenerator.invalidCardOwner());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorOwnerField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/4
    // 17. Неуспешная покупка тура – буквы русского алфавита
    public void errorPath_invalidCardOwnerRusLanguage_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setOwner(CardDataGenerator.invalidCardOwnerRusLanguage());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorOwnerField();
    }

    @Test
    // 18. Неуспешная покупка тура – не валидный код (2 цифры).
    public void errorPath_invalidCvcCvv_validAnotherFields_CASH() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setCvcCvv(CardDataGenerator.invalidCvcCvv());

        Page mainPage = new Page();
        mainPage.clickCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCvcCvvField();
    }

    // ПОКУПКА ТУРА В КРЕДИТ, КНОПКА "КУПИТЬ В КРЕДИТ"

    @Test // ИШЬЮ
    // 19. Успешная покупка тура в кредит с валидным номером карты с разрешенной оплатой (APPROVED).
    public void happyPath_validAllFields_approvedCard_CREDIT() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
        Assertions.assertTrue(SQLDataGenerator.approvedCredit());
    }

    @Test
    // 20. Успешная покупка тура в кредит – месяц и год равны текущим.
    public void happyPath_validAllFields_approvedCard_now_CREDIT() {
        Card approvedCard = new Card();
        approvedCard.setCardNumber(CardDataGenerator.approveCardNumber());
        approvedCard.setMonth(CardDataGenerator.nowMonth());
        approvedCard.setYear(CardDataGenerator.nowYear());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(approvedCard);
        mainPage.clickContinueBottom();
        mainPage.happyPurchase();
        Assertions.assertTrue(SQLDataGenerator.approvedCredit());
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/2 ЕЩЕ ОДНО ИШЬЮ
    // 21. Неуспешная покупка тура в кредит с валидным номером карты с запрещенной оплатой (DECLINED).
    public void errorPath_validAllFields_declinedCard_CREDIT() {
        Card declinedCard = new Card();
        declinedCard.setCardNumber(CardDataGenerator.declineCardNumber());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(declinedCard);
        mainPage.clickContinueBottom();
        mainPage.errorPurchase();
        Assertions.assertTrue(SQLDataGenerator.declinedCredit());
    }

    @Test
    // 22. Неуспешная покупка тура в кредит с пустым полем "Номер карты".
    public void errorPath_emptyCardNumber_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber("");

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyCardNumberField();
    }

    @Test
    // 23. Неуспешная покупка тура в кредит с пустым полем "Месяц".
    public void errorPath_emptyMonth_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setMonth("");

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyMonthField();
    }

    @Test
    // 24. Неуспешная покупка тура в кредит с пустым полем "Год".
    public void errorPath_emptyYear_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setYear("");

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyYearField();
    }

    @Test
    // 25. Неуспешная покупка тура в кредит с пустым полем "Владелец".
    public void errorPath_emptyOwner_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setOwner("");

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyOwnerField();
    }

    @Test
    // 26. Неуспешная покупка тура в кредит с пустым полем "CVC/CVV".
    public void errorPath_emptyCvcCvv_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCvcCvv("");

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorEmptyCvcCvvField();
    }

    @Test
    // 27. Неуспешная покупка тура в кредит с не валидным номером карты (15 цифр).
    public void errorPath_invalidCardNumber15_validAnotherFields15_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber15());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCardNumberField();
    }

    @Test
    // 28. Неуспешная покупка тура в кредит с не валидным номером карты (1 цифрa).
    public void errorPath_invalidCardNumber1_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber1());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCardNumberField();
    }

    @Test
    // 29. Неуспешная покупка тура в кредит с не валидным номером карты. (16 цифр)
    public void errorPath_invalidCardNumber_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.invalidCardNumber());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPurchase();
    }

    @Test
    // 30. Неуспешная покупка тура в кредит – месяц и год меньше текущих.
    public void errorPath_invalidPastYear_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setYear(CardDataGenerator.invalidYearPast());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPastYearField();
    }

    @Test
    // 31. Неуспешная покупка тура в кредит – месяц и год больше текущих на 6 и более лет.
    public void errorPath_invalidFutureYear_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setYear(CardDataGenerator.invalidYearFuture());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorPastYearFutureField();
    }

    @Test
    // 32. Неуспешная покупка тура в кредит – не валидный месяц.
    public void errorPath_invalidMonth_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setMonth(CardDataGenerator.invalidMonth());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorMonthField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/3
    // 33. Неуспешная покупка тура в кредит – нулевое значение месяца "00".
    public void errorPath_invalidNullMonth_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setMonth(CardDataGenerator.nullMonth());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorMonthField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/4
    // 34. Неуспешная покупка тура в кредит – цифры и нерелевантные символы
    public void errorPath_invalidCardOwner_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setOwner(CardDataGenerator.invalidCardOwner());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorOwnerField();
    }

    @Test // issue: https://github.com/chugad/qa-diploma/issues/4
    // 35. Неуспешная покупка тура в кредит – буквы русского алфавита
    public void errorPath_invalidCardOwnerRusLanguage_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setOwner(CardDataGenerator.invalidCardOwnerRusLanguage());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorOwnerField();
    }

    @Test
    // 36. Неуспешная покупка тура в кредит – не валидный код (2 цифры).
    public void errorPath_invalidCvcCvv_validAnotherFields_CREDIT() {
        Card newCard = new Card();
        newCard.setCardNumber(CardDataGenerator.approveCardNumber());
        newCard.setCvcCvv(CardDataGenerator.invalidCvcCvv());

        Page mainPage = new Page();
        mainPage.clickCreditCardPayBottom();
        mainPage.insertCardData(newCard);
        mainPage.clickContinueBottom();
        mainPage.errorCvcCvvField();
    }

    @AfterEach
    public void cleanTables() throws SQLException {
        SQLDataGenerator.cleanTables();
    }

}