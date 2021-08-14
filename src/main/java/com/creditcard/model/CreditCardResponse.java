package com.creditcard.model;

import lombok.*;


@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
public class CreditCardResponse {

    private String name;

    private String creditCardNumber;

    private int balance;

    private int limit;

    public CreditCardResponse() {
    }

}
