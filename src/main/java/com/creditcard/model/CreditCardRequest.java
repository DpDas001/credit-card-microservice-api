package com.creditcard.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@Data
@Valid
@AllArgsConstructor
public class CreditCardRequest {

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull
    @Size(min=1, max=19, message = "Card number length must be between 1 and 19")
    private String creditCardNumber;
    private int balance;
    @NotNull
    private int limit;


}
