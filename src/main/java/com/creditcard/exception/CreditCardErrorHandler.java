package com.creditcard.exception;

import com.creditcard.constants.CreditCardConstant;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;


@ControllerAdvice
public class CreditCardErrorHandler {

    @ExceptionHandler(RecordExistException.class)
    public ResponseEntity<ErrorData> handleRecordExistException() {
        String errorMessage = getLocalizedErrorMessage(CreditCardConstant.ERROR_MSG_KEY);
        return new ResponseEntity<ErrorData>(new ErrorData(CreditCardConstant.RECORD_EXIST_ERROR_CODE, CreditCardConstant.RECORD_EXIST_CODE, errorMessage), CONFLICT);
    }


    @ExceptionHandler(InvalidCardNumberException.class)
    public ResponseEntity<ErrorData> handleInvalidCardNumberException() {
        String errorMessage = getLocalizedErrorMessage(CreditCardConstant.INVALID_CARD_NUMBER_MSG_KEY);
        return new ResponseEntity<ErrorData>(new ErrorData(CreditCardConstant.INVALID_CARD_NUMBER_ERROR_CODE, CreditCardConstant.INVALID_CARD_NUMBER_CODE, errorMessage), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        StringBuffer stringBuffer =new StringBuffer();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            stringBuffer.append(fieldName+" "+errorMessage+" ");
        });
        return new ResponseEntity<ErrorData>(new ErrorData(CreditCardConstant.INVALID_INPUT_ERROR_CODE, CreditCardConstant.INVALID_INPUT_CODE, stringBuffer.toString()), BAD_REQUEST);
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorData> handleMissingRequestHeaderException(
            MissingRequestHeaderException ex) {
        return new ResponseEntity<ErrorData>(new ErrorData(CreditCardConstant.MISSING_HEADER_ERROR_CODE, CreditCardConstant.MISSING_HEADER_CODE, ex.getMessage()), BAD_REQUEST);
    }
    private String getLocalizedErrorMessage(String key) {
        return ResourceBundle.getBundle(CreditCardConstant.ERROR_MSG_BUNDLE, LocaleContextHolder.getLocale()).getString(key);
    }
}
