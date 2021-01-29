package ru.netology;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    // Номер карты

    public static String approveCardNumber() {
        return "4444444444444441";
    }

    public static String declineCardNumber() {
        return "4444444444444442";
    }

    public static String invalidCardNumber() {
        return String.valueOf(faker.number().randomNumber(faker.number().numberBetween(1,15), true));
    }

    public static String randomCardNumber() {
        return faker.business().creditCardNumber();
    }

    // Месяц окончания срока действия карты

    public static String nowMonth() {

        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String randomMonth() {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1,12)).format(DateTimeFormatter.ofPattern("MM"));
    }

    // Год окончания срока действия карты

    public static String nowYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String pastYear() {
        return LocalDate.now().minusYears(faker.number().numberBetween(1,5)).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String futureValidYear() { // срок действия карты обычно составляет 4 года
        return LocalDate.now().plusYears(faker.number().numberBetween(1,4)).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String futureInvalidYear() { // срок действия карты обычно составляет 4 года
        return LocalDate.now().plusYears(faker.number().numberBetween(5,50)).format(DateTimeFormatter.ofPattern("YY"));
    }

    // Имя и Фамилия владельца карты

    public static String validCardOwner() {
        return faker.name().fullName();
    }

    public static String invalidCardOwner() {
        Faker faker = new Faker(new Locale("ru"));
        String randomName1 = faker.name().lastName();
        String randomName2 = faker.internet().password();
        String randomName = randomName1 + " " + randomName2;
        return randomName;
    }

    // CVC/CVV код

    public static String validCvcCvv() {
        return String.format("%03d", faker.number().randomNumber(3,true));
    }

    public static String invalidCvcCvv() {
        return String.valueOf(faker.number().numberBetween(0,99));
    }
}
