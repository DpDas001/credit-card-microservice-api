package com.creditcard.service;

import com.creditcard.model.CreditCardRequest;
import com.creditcard.model.CreditCardResponse;

import java.util.List;

public interface CreditCardService {

    void addCardDetails(CreditCardRequest creditCardRequest, String correlationId);

    List<CreditCardResponse> fetchAllCardDetails(String correlationId);
}
