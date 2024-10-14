package com.jdc.balance.paymentgateway.util;

import com.jdc.balance.paymentgateway.dto.AccountDto;
import com.jdc.balance.paymentgateway.entity.Account;
import org.springframework.beans.BeanUtils;

public class EntityUtil {

    public static AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account, accountDto);
        return accountDto;
    }

    public static Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }
}
