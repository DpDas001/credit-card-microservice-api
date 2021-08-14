package com.creditcard.repository;

import com.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

    CreditCard findByCreditCardNumber(String creditCardNumber);
}