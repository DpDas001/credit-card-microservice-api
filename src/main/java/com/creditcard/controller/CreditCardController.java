package com.creditcard.controller;

import com.creditcard.constants.CreditCardConstant;
import com.creditcard.model.CreditCardRequest;
import com.creditcard.model.CreditCardResponse;
import com.creditcard.service.CreditCardService;
import com.creditcard.service.CreditCardServiceImpl;
import com.creditcard.validation.CardNumberValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController
@Validated
@CrossOrigin(origins = "*")
public class CreditCardController {

    Logger LOGGER = LogManager.getLogger(CreditCardController.class);

    @Autowired
    CreditCardService creditCardService;


    @GetMapping(value = "/info")
    public String index() {
        return "Server is running!";
    }


    @GetMapping(value = "/all")
    @ResponseStatus(OK)
    public ResponseEntity<List<CreditCardResponse>> get(@RequestHeader(name = "txn-correlation-id", required = true) String correlationId) {
        LOGGER.info("POST get method Entry ----> "+correlationId);
        return new ResponseEntity<List<CreditCardResponse>>(creditCardService.fetchAllCardDetails(correlationId), OK);
    }


    @PostMapping(value = "/add")
    @ResponseStatus(CREATED)
    @Valid
    public ResponseEntity<String> save(@RequestHeader(name = "txn-correlation-id", required = true) String correlationId, @RequestBody @Valid CreditCardRequest creditCardRequest){
        LOGGER.debug("correlationId "+correlationId+" creditCardRequest payload "+creditCardRequest);
        LOGGER.info("POST save method Entry ----> "+correlationId);
        creditCardService.addCardDetails(creditCardRequest, correlationId);
        LOGGER.info("POST save method Exit ----> "+correlationId);
        return new ResponseEntity<String>(CreditCardConstant.SUCCESS, CREATED);
    }

}