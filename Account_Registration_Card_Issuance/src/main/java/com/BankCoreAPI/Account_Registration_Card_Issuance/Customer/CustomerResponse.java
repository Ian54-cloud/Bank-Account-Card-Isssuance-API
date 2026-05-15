package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountType;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String iban;
    private String swift;
    private String holderName;
    private Currency currency;
    private AccountType accountType;
    private LocalDate creationDate=LocalDate.now();

    private String cardNumber;
    private String cvv;
    private DateTimeFormatter expiryDate;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setExpiryDate(DateTimeFormatter expiryDate) {
        this.expiryDate = expiryDate;
    }

    public DateTimeFormatter getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getHolderName() {
        return holderName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getSwift() {
        return swift;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
