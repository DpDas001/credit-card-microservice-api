package com.creditcard.service;

import com.creditcard.controller.CreditCardController;
import com.creditcard.entity.CreditCard;
import com.creditcard.exception.InvalidCardNumberException;
import com.creditcard.exception.RecordExistException;
import com.creditcard.mapper.CreditCardResponseMapper;
import com.creditcard.model.CreditCardRequest;
import com.creditcard.model.CreditCardResponse;
import com.creditcard.repository.CreditCardRepository;
import com.creditcard.validation.CardNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Service
public class CreditCardServiceImpl implements CreditCardService {

    Logger LOGGER = LogManager.getLogger(CreditCardServiceImpl.class);

    @Autowired
    CreditCardRepository repository;

    @Autowired
    private CreditCardResponseMapper creditCardResponseMapper;

    @Autowired
    CardNumberValidation cardNumberValidation;

    public void addCardDetails(CreditCardRequest creditCardRequest, String correlationId) {
        LOGGER.info("addCardDetails method Entry correlationId "+correlationId);
        cardNumberValidationCheck(creditCardRequest, correlationId);

        cardNumberExistCheck(creditCardRequest, correlationId);

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(creditCardRequest.getCreditCardNumber());
        creditCard.setName(creditCardRequest.getName());
        creditCard.setBalance(creditCardRequest.getBalance());
        creditCard.setLimit(creditCardRequest.getLimit());
        repository.save(creditCard);
        LOGGER.info("addCardDetails method Exit correlationId "+correlationId+ " Card details has been added successfully ");
    }

    private void cardNumberExistCheck(CreditCardRequest creditCardRequest, String correlationId) {
        Boolean isCardNumberExist = repository.findByCreditCardNumber(creditCardRequest.getCreditCardNumber()) == null ? Boolean.FALSE : Boolean.TRUE;
        if (isCardNumberExist) {
            LOGGER.error("cardNumberExistCheck correlationId "+correlationId+ " InvalidCardNumberException "+ creditCardRequest.getCreditCardNumber());
            throw new RecordExistException();
        }
    }

    private void cardNumberValidationCheck(CreditCardRequest creditCardRequest, String correlationId) {
        if(!cardNumberValidation.isValid(creditCardRequest.getCreditCardNumber())){
            LOGGER.error("cardNumberValidationCheck correlationId "+correlationId+ " InvalidCardNumberException "+ creditCardRequest.getCreditCardNumber());
            throw new InvalidCardNumberException();
        }
    }

    public List<CreditCardResponse> fetchAllCardDetails(String correlationId) {

        LOGGER.info("fetchAllCardDetails method Entry correlationId "+correlationId);
        List<CreditCardResponse> CreditCardResponseList = repository.findAll().stream().map(creditCard -> creditCardResponseMapper.map(creditCard))
                .collect(Collectors.toList());
        LOGGER.info("fetchAllCardDetails method Exit correlationId "+correlationId+ " CreditCardResponseList size " + CreditCardResponseList.size());
        return CreditCardResponseList;

    }
}