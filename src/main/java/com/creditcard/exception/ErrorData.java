package com.creditcard.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NONE;


@JsonIgnoreProperties
@JsonTypeName("error")
@JsonTypeInfo(include = PROPERTY, use = NONE)
@Data
@ToString
@NoArgsConstructor
public class ErrorData {

    @JsonProperty("error")
    private Error error = new Error();

    public ErrorData(Integer statusCode, String code, String message) {
        this.error.statusCode = statusCode;
        this.error.code = code;
        this.error.message = message;
    }

    @Data
    public class Error {

        @JsonProperty("statusCode")
        private Integer statusCode;

        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;

    }
}