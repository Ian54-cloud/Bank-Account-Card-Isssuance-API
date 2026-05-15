package com.BankCoreAPI.Account_Registration_Card_Issuance.Card;

import com.BankCoreAPI.Account_Registration_Card_Issuance.Account.AccountEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardService {
    private final CardRepository cardRepository;
    public CardService(CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }
    private String generateCardNumber(){
        CardEntity cardEntity=new CardEntity();
        StringBuilder cardNumber=new StringBuilder(29);

        ThreadLocalRandom rnd=ThreadLocalRandom.current();
        for(int i=0;i<24;i++){
            if(i==0){
                cardNumber.append(rnd.nextInt(1,10));
            }else{
                cardNumber.append(rnd.nextInt(10));
            }
            if((i+1)%4==0 && i!=23){
                cardNumber.append(" ");
            }
        }
        cardEntity.setCardNumber(cardNumber.toString());
        cardRepository.save(cardEntity);
        return cardNumber.toString();
    }
    private String generateCVV(){
        CardEntity cardEntity=new CardEntity();
        Random rnd=new Random();
        int cvv=rnd.nextInt(100,1000);
        String formatted_cvv=String.valueOf(cvv);
        cardEntity.setCvv(formatted_cvv);
        cardRepository.save(cardEntity);
        return formatted_cvv;
    }
    private LocalDate calculateRandomExpiryDate() {
        int randomMonths = ThreadLocalRandom.current().nextInt(1, 49);
        YearMonth randomExpiryMonth = YearMonth.now().plusMonths(randomMonths);

        // Banking standard: Cards expire on the last day of the given month
        return randomExpiryMonth.atEndOfMonth();
    }
    public CardEntity createCard(AccountEntity account){
        CardEntity card=new CardEntity();
        card.setAccountEntity(account);

        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCVV());
        card.setExpiryDate(calculateRandomExpiryDate());
return card;
}
}
