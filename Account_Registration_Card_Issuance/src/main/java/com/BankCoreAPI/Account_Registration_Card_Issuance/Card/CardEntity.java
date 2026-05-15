package com.BankCoreAPI.Account_Registration_Card_Issuance.Card;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, length = 19)
    private String cardNumber;

    @Column(length = 3)
    private String cvv;

    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name="account_id")
    private AccountEntity accountEntity;


    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public UUID getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
