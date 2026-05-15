package com.BankCoreAPI.Account_Registration_Card_Issuance.Account;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Customer.CustomerEntity;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Customer.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class AccountService{
    private final AccountRepository accountRepository;
    private final AccountType accountType;
    private final Currency currency;
    private final CustomerRequest customerRequest;


    public AccountService(AccountRepository accountRepository,AccountType accountType, Currency currency, CustomerRequest customerRequest) {
        this.accountRepository = accountRepository;
        this.accountType=accountType;
        this.currency=currency;
        this.customerRequest=customerRequest;
    }
    private String generateIban(){
        // accountEntity=new AccountEntity();
        Random rnd=new Random();
        String countryCode="PL";
        int two_digits=rnd.nextInt(100);
        int national_bank_code=1160;
        int branch_identity_code=rnd.nextInt(10000);
        int verification_digit=rnd.nextInt(10);
        StringBuilder sb=new StringBuilder(16);
        for(int i=0;i<16;i++){
            sb.append(rnd.nextInt(10));
        }
        String accountDigits=sb.toString();
        String iban= countryCode + two_digits + national_bank_code + branch_identity_code + verification_digit + accountDigits;
        //accountEntity.setIban(iban);
        //accountRepository.save(accountEntity);
        return iban;
    }
    private String generateSwift() {
        //AccountEntity accountEntity = new AccountEntity();
        //GENERATE SWIFT code
        String bankCode = "BIGB";
        String locationCode = "PW";
        String branchCode = "XXX";
        String countryCode = "PL";
        String swiftCode = bankCode + countryCode + locationCode + branchCode;

        /*accountEntity.setSwift(swiftCode);
        accountEntity.setHolderName(customerRequest.getFullName());
        accountEntity.setAccountType(customerRequest.getAccountType());
        accountEntity.setCurrency(customerRequest.getCurrency());


        accountRepository.save(accountEntity);*/

        return swiftCode;

    }
    public AccountEntity createAccount(CustomerEntity customer){
        AccountEntity account=new AccountEntity();

        account.setCustomer(customer);
        account.setHolderName(customer.getFullName());
        account.setAccountType(customer.getAccountType());
        account.setCurrency(customer.getCurrency());

        account.setIban(generateIban());
        account.setSwift(generateSwift());

        return accountRepository.save(account);
    }

    }
