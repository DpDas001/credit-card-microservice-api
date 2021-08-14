package com.creditcard.mapper;

import com.creditcard.entity.CreditCard;
import com.creditcard.model.CreditCardResponse;


public interface CreditCardResponseMapper {

    CreditCardResponse map(CreditCard creditCard);
}
