package com.fubc.soroncho.bankingapp.services;

import com.fubc.soroncho.bankingapp.model.Account;
import com.fubc.soroncho.bankingapp.model.AggregateResults;
import com.fubc.soroncho.bankingapp.model.Customer;
import com.fubc.soroncho.bankingapp.repositories.AccountItems;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface AccountService {

    Page<Account> getAllAccountsPaged(int pageNo);

    Account saveAccount(Account account);

    void deleteCustomer(Long accountId);

    Account findById(Long AccoundId);

    Page<Account> search(String search, int pageNo);

    List<AccountItems>  bankStatus();
    Double  liquidity();

    public List<Account> getAllAccounts(Customer customer);

    void deposit(Long account, Double amount);

    Account findByAcc(Long account);
}
