package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountType;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String fullName;
    private String peselNumber;
    private Nationality nationality;
    private DocumentType documentType;
    private LocalDate expiryDate;
    private LocalDate birthDate;
    private AccountType accountType;
    private String documentNumber;
private Currency currency;

    //GETTERS AND SETTERS


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }


    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }



    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

}
