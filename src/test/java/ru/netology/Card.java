package ru.netology;

import lombok.Data;

@Data
public class Card {
    private String cardNumber;
    private String month;
    private String year;
    private String owner;
    private String cvcCvv;

    public Card() {
        cardNumber = CardDataGenerator.randomCardNumber();
        month = CardDataGenerator.validMonth();
        year = CardDataGenerator.validYear();
        owner = CardDataGenerator.validCardOwner();
        cvcCvv = CardDataGenerator.validCvcCvv();
    }
}
