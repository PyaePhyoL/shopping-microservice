package com.jdc.balance.paymentgateway.dao;

import com.jdc.balance.paymentgateway.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account, Integer> {

    @Query("""
    select a from Account a where a.name = :name and a.cardNumber = :cardNumber
""")
    Optional<Account> findAccountByNameAndCardNumber(@Param("name") String name, @Param("cardNumber") String cardNumber);

    @Query("""
    select a.amount from Account a where a.name = :name and a.cardNumber = :cardNumber
""")
    double getBalance(@Param("name") String name, @Param("cardNumber") String cardNumber);

    boolean existsByNameAndCardNumber(String name, String cardNumber);
}
