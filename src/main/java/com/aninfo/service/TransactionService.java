package com.aninfo.service;

import com.aninfo.enums.TransactionType;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private Double PROMO_MIN_AMOUNT = Double.valueOf(2000);

    private Double PROMO_LIMIT = Double.valueOf(500);

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) {
        TransactionType type = transaction.getType();
        Double amount = transaction.getAmount();


        if(type.equals(TransactionType.DEPOSIT)){
            if(amount >= PROMO_MIN_AMOUNT) {
                amount += Math.min(amount/10,PROMO_LIMIT);
                transaction.setAmount(amount);
            }
            accountService.deposit(transaction.getAccount().getCbu(),amount);
        }
        if(type.equals(TransactionType.WITHDRAW)){
            accountService.withdraw(transaction.getAccount().getCbu(),amount);
        }

        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

}
