package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
