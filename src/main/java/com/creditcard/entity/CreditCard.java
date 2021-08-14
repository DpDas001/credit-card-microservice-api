package com.creditcard.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "CREDIT_CARD")
@SequenceGenerator(name = "creditCardSequence", allocationSize = 1, sequenceName = "CREDIT_CARD_SEQ_GEN")
@Data
public class CreditCard {

    @Id
    @Column(name = "CREDIT_CARD_SEQ_ID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditCardSequence")
    private int creditCardSeqId;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String name;

    @Column(name = "CARD_BALANCE", nullable = false)
    private int balance;

    @Column(name = "CARD_LIMIT", nullable = false)
    private int limit;

    @Column(name = "CARD_NUMBER", nullable = false, unique = true)
    private String creditCardNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CreditCard creditCard = (CreditCard) o;
        return creditCardNumber == creditCard.creditCardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardNumber);
    }

}
