package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByPeselNumber(String peselNumber);

}
