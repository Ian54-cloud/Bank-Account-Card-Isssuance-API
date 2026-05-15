package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class IncorrectPeselNumberException extends RuntimeException {
    public IncorrectPeselNumberException(String message) {
        super(message);
    }
}
