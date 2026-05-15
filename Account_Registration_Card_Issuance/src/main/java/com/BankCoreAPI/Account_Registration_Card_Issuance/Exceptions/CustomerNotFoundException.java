package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
