package jpa.transactional.mapper;

import jpa.transactional.dto.AccountDto;
import jpa.transactional.entity.Account;

public class AccountMapper {
    public static Account mapAccountDto(AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getOwner(),
                accountDto.getBalance()
        );
    }

    public static AccountDto mapAccount(Account account) {
        return new AccountDto(
                account.getId(),
                account.getOwner(),
                account.getBalance()
        );
    }
}
