package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.SlackEmoji;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CardDataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    // Номер карты

    public static String approveCardNumber() {
        return "4444444444444441";
    }

    public static String declineCardNumber() {
        return "4444444444444442";
    }

    public static String invalidCardNumber1() {
        return String.valueOf(faker.number().randomNumber(1, true));
    }

    public static String invalidCardNumber15() {
        return String.valueOf(faker.number().randomNumber(15, true));
    }

    public static String invalidCardNumber() {
        return String.valueOf(faker.number().randomNumber(16, true));
    }

    public static String randomCardNumber() {
        return faker.business().creditCardNumber();
    }

    // Месяц окончания срока действия карты

    public static String nowMonth() {

        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String nullMonth() {

        return "00";
    }

    public static String validMonth() {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1,12)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String invalidMonth() {
        return String.valueOf(faker.number().numberBetween(13,99));
    }

    // Год окончания срока действия карты

    public static String nowYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String invalidYearPast() {
        return LocalDate.now().minusYears(faker.number().numberBetween(1,5)).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String validYear() { // срок действия карты обычно составляет 4 года
        return LocalDate.now().plusYears(faker.number().numberBetween(0,4)).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String invalidYearFuture() { // срок действия карты обычно составляет 4 года
        return LocalDate.now().plusYears(faker.number().numberBetween(6,50)).format(DateTimeFormatter.ofPattern("YY"));
    }

    // Имя и Фамилия владельца карты

    public static String validCardOwner() {
        return faker.name().fullName();
    }

    public static String invalidCardOwner() {
        Faker faker = new Faker(new Locale("es"));
        String randomName1 = faker.name().lastName();
        String randomName2 = faker.internet().password();
        SlackEmoji randomName3 = faker.slackEmoji();
        String randomName = randomName1 + " " + randomName2 + " " + randomName3;
        return randomName;
    }

    public static String invalidCardOwnerRusLanguage() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    // CVC/CVV код

    public static String validCvcCvv() {
        return String.format("%03d", faker.number().randomNumber(3,true));
    }

    public static String invalidCvcCvv() {
        return String.valueOf(faker.number().numberBetween(0,99));
    }
}
