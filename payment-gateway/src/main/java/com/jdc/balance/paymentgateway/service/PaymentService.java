package com.jdc.balance.paymentgateway.service;

import com.jdc.balance.paymentgateway.dao.AccountDao;
import com.jdc.balance.paymentgateway.entity.Account;
import com.jdc.balance.paymentgateway.exception.InsufficientAmountException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountDao accountDao;

    public Account createAccount(String name, double amount) {
        Account account = new Account();
        account.setCardNumber(generateCardNumber());
        account.setName(name);
        account.setAmount(amount);
        return accountDao.save(account);
    }

    public Account getAccount(String name, String cardNumber) {
        return accountDao.findAccountByNameAndCardNumber(name, cardNumber)
                .orElseThrow(EntityNotFoundException::new);
    }

    public boolean existsAccount(String name, String cardNumber) {
        return accountDao.existsByNameAndCardNumber(name, cardNumber);
    }

    public double getBalance(String name, String cardNumber) {
        return accountDao.getBalance(name, cardNumber);
    }

    private String generateCardNumber(){
        int desiredLength = 7;
        return RandomStringUtils.randomNumeric(desiredLength);
    }


    @Transactional
    public void deposit(String name, double amount, String cardNumber) {
        Account account = getAccount(name, cardNumber);
        account.setAmount(account.getAmount() + amount);
    }

    @Transactional
    public void withdraw(String name, double amount, String cardNumber) {
        var currentBalance = getBalance(name, cardNumber);
        if (currentBalance < amount) {
            throw new InsufficientAmountException("Not Enough Money");
        } else {
            currentBalance -= amount;
        }
        Account account = getAccount(name, cardNumber);
        account.setAmount(currentBalance);
    }

    @Transactional
    public double buyItemsTransaction(String name, double amount, String cardNumber,
                                       String owner, String ownerCardNumber) {
        deposit(owner, amount, ownerCardNumber);
        withdraw(name, amount, cardNumber);
        return amount;
    }
}
