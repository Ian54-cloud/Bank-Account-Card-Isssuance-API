package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class IncorrectAccountTypeException extends RuntimeException {
    public IncorrectAccountTypeException(String message) {
        super(message);
    }
}
