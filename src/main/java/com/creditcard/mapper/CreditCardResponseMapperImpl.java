package com.creditcard.mapper;

import com.creditcard.entity.CreditCard;
import com.creditcard.model.CreditCardResponse;
import org.springframework.stereotype.Component;

@Component
public class CreditCardResponseMapperImpl implements  CreditCardResponseMapper {


    @Override
    public CreditCardResponse map(CreditCard creditCard) {
        if(creditCard == null) {
             return null;
        }
        CreditCardResponse creditCardResponse = new CreditCardResponse();

        creditCardResponse.setCreditCardNumber(creditCard.getCreditCardNumber());
        creditCardResponse.setName(creditCard.getName());
        creditCardResponse.setLimit(creditCard.getLimit());
        creditCardResponse.setBalance(creditCard.getBalance());

        return creditCardResponse;
    }
}
