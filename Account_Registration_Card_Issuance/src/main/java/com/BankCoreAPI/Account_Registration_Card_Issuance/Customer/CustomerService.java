package com.BankCoreAPI.Account_Registration_Card_Issuance.Customer;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.*;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Card.CardEntity;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Card.CardService;
import com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Exceptions.AccountNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final CardService cardService;
    public CustomerService(CustomerRepository customerRespository, AccountRepository accountRepository, AccountService accountService, CardService cardService){
        this.customerRepository=customerRespository;
        this.accountRepository=accountRepository;
        this.accountService=accountService;
        this.cardService=cardService;
    }
    public String validateData(CustomerRequest customerRequest) {
        //1. Name mustn't be empty and must have spaces
        if(customerRequest.getFullName()==null || customerRequest.getFullName().isEmpty() || customerRequest.getFullName().contains(" ")){
            throw new IncorrectNameException("the name mustn't be empty and must have spaces");
        }
        if(customerRequest.getPeselNumber().length()!=11){
            throw new IncorrectPeselNumberException("the pesel number must be 11-digits");
        }
        if(!customerRequest.getNationality().equals(Nationality.PL) || !customerRequest.getNationality().equals(Nationality.POLISH)){
            throw new ForeignNationalityException("Dear," +customerRequest.getFullName()+ ",You should open an account directly to the nearest branch");
        }
        if(!customerRequest.getDocumentType().equals(DocumentType.PASSPORT) || !customerRequest.getDocumentType().equals(DocumentType.DOWOD_OSOBISTY)){
            throw new InvalidDocumentException("You should use either Passport or Polish Identity Card");
        }
        if(customerRequest.getDocumentNumber()==null || customerRequest.getDocumentNumber().isEmpty()){
            throw new InvalidDocumentException("the document number musn't be empty");
        }
        LocalDate expiry_date=customerRequest.getExpiryDate();
        if(expiry_date.isBefore(LocalDate.now().plusMonths(3))){
            throw new InvalidExpiryDateException("the document must be valid at least three months");
        }
        if(!customerRequest.getAccountType().equals(AccountType.PERSONAL) || !customerRequest.getAccountType().equals(AccountType.SAVINGS)){
            throw new IncorrectAccountTypeException("the account type must be either SAVINGS or PERSONAL");
        }
        if(!customerRequest.getCurrency().equals(Currency.EUR) || !customerRequest.getCurrency().equals(Currency.PLN) || !customerRequest.getCurrency().equals(Currency.GBP) || !customerRequest.getCurrency().equals(Currency.GBP)){
            throw new InvalidCurrencyException("this currency isnt available on our bank");
        }

        LocalDate customerBirthDate = customerRequest.getBirthDate();
        int ageYears = Period.between(customerBirthDate, LocalDate.now()).getYears();
        if (ageYears < 18) {
            throw new IncorrectAgeException("The age must be 18 years old or older");
        }


        return "data saved!";
    }
    //register customer and return account details
    @Transactional
    public CustomerResponse RegisterCustomer(CustomerRequest customerRequest){
        AccountEntity accountEntity=new AccountEntity();
        CardEntity cardEntity=new CardEntity();
        validateData(customerRequest);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customerRequest.getFullName());
        customerEntity.setBirthDate(customerRequest.getBirthDate());
        customerEntity.setPeselNumber(customerRequest.getPeselNumber());
        customerEntity.setNationality(customerRequest.getNationality());
        customerEntity.setDocumentType(customerRequest.getDocumentType());
        customerEntity.setDocumentNumber(customerRequest.getDocumentNumber());
        customerEntity.setExpiryDate(customerRequest.getExpiryDate());
        customerEntity.setAccountType(customerRequest.getAccountType());
        customerEntity.setCurrency(customerRequest.getCurrency());
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        accountService.createAccount(savedCustomer);

        // 4. FIXED: Generate card details using CardService orchestration
        cardService.createCard(accountEntity);

        return mapToCustomerResponse(accountEntity, cardEntity);
    }
    public CustomerResponse getAccountDetails(String peselNumber) {

        // 1. Find CustomerEntity profile metadata using PESEL key
        CustomerEntity customerEntity = customerRepository.findByPeselNumber(peselNumber)
                .orElseThrow(() -> new CustomerNotFoundException("The pesel number that you inserted isn't linked to any account"));

        // 2. Query target bank account matching customer profile ID key
        AccountEntity accountEntity = accountRepository.findByCustomer(customerEntity)
                .orElseThrow(() -> new AccountNotFoundException("No active account matching this customer profile."));

        // 3. Fallback extraction safely identifying cards mapped inside the account entity reference collection
        CardEntity cardEntity = null;
        if (accountEntity.getCards() != null && !accountEntity.getCards().isEmpty()) {
            cardEntity = accountEntity.getCards().get(0);
        }

        return mapToCustomerResponse(accountEntity, cardEntity);
    }
    private CustomerResponse mapToCustomerResponse(AccountEntity account, CardEntity card) {
        CustomerResponse response = new CustomerResponse();
        response.setIban(account.getIban());
        response.setSwift(account.getSwift());
        response.setHolderName(account.getHolderName());
        response.setCurrency(account.getCurrency());
        response.setAccountType(account.getAccountType());

        if (card != null) {
            response.setCardNumber(card.getCardNumber());
            response.setCvv(card.getCvv());

            if (card.getExpiryDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                response.setExpiryDate(formatter);
            }
        }
        return response;
    }





}