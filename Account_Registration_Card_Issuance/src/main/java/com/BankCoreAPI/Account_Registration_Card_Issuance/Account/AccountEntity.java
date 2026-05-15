package com.BankCoreAPI.Account_Registration_Card_Issuance.Account;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Card.CardEntity;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Customer.CustomerEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // FIXED: Changed IDENTITY to UUID
    private UUID id;

    private String iban;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String swift;

    @Enumerated(EnumType.STRING) // ADDED: Ensures enum names map to strings in database
    private Currency currency;

    private String holderName;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CardEntity> cards;

    @CreationTimestamp
    private LocalDate creationDate;

    // ADDED: Missing setter required by your Service layer to link accounts to customers
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }

    public UUID getId() {
        return id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setHolderName(String holderName){
        this.holderName = holderName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getIban() {
        return iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getHolderName() {
        return holderName;
    }
}

