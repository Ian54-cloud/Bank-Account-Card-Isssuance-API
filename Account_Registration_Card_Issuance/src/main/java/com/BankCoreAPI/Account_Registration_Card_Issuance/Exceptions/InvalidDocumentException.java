package com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException(String message) {
        super(message);
    }
}
