package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class InvalidExpiryDateException extends RuntimeException {
    public InvalidExpiryDateException(String message) {
        super(message);
    }
}
