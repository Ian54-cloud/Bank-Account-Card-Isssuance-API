package com.BankCoreAPI.Account_Registration_Card_Issuance.Account;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Customer.CustomerEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByCustomer(CustomerEntity customer);
    }




