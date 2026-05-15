package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountEntity;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountType;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@Table(name="customers")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        private String fullName;
        @Column(length=11, unique=true)
        private String peselNumber;
        private Nationality nationality;
        @Enumerated(EnumType.STRING)
        private DocumentType documentType;
        private String documentNumber;
        private LocalDate expiryDate;
        private LocalDate birthDate;
        @Enumerated(EnumType.STRING)
        @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
        private List<AccountEntity> accounts;
        private Currency currency;

        public void setCurrency(Currency currency) {
                this.currency = currency;
        }

        public Currency getCurrency() {
                return currency;
        }

        private AccountType accountType;

        public void setAccountType(AccountType accountType) {
                this.accountType = accountType;
        }

        public AccountType getAccountType() {
                return accountType;
        }

        public void setFullName(String fullName) {
                this.fullName = fullName;
        }

        public String getFullName() {
                return fullName;
        }

        public void setBirthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
        }

        public LocalDate getExpiryDate() {
                return expiryDate;
        }

        public void setExpiryDate(LocalDate expiryDate) {
                this.expiryDate = expiryDate;
        }

        public void setDocumentType(DocumentType documentType) {
                this.documentType = documentType;
        }

        public DocumentType getDocumentType() {
                return documentType;
        }

        public void setDocumentNumber(String documentNumber) {
                this.documentNumber = documentNumber;
        }

        public String getDocumentNumber() {
                return documentNumber;
        }

        public String getPeselNumber() {
                return peselNumber;
        }

        public void setPeselNumber(String peselNumber) {
                this.peselNumber = peselNumber;
        }

        public Nationality getNationality() {
                return nationality;
        }

        public void setNationality(Nationality nationality) {
                this.nationality = nationality;
        }
}

