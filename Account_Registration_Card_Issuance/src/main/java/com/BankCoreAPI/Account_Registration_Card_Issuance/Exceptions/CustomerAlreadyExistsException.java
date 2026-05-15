package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
