package com.fubc.soroncho.bankingapp.services.servimple;


import com.fubc.soroncho.bankingapp.model.Account;
import com.fubc.soroncho.bankingapp.model.Customer;
import com.fubc.soroncho.bankingapp.repositories.AccountItems;
import com.fubc.soroncho.bankingapp.repositories.AccountRepository;
import com.fubc.soroncho.bankingapp.repositories.CustomerRepository;
import com.fubc.soroncho.bankingapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accRepo;
    @Autowired
    private CustomerRepository cusRepo;

    @Override
    public Page<Account> getAllAccountsPaged(int pageNo) {
        return accRepo.findAll(PageRequest.of(0,30, Sort.by("accountNumber")));
    }

    @Override
    public Account saveAccount(Account account) {
        return accRepo.save(account);
    }

    @Override
    public void deleteCustomer(Long accountId) {

    }

    @Override
    public Account findById(Long accountId) {
        return accRepo.findById(accountId).orElse(new Account());
    }

    @Override
    public Page<Account> search(String search, int pageNo) {
        return null;
    }

    @Override
    public List<AccountItems> bankStatus() {

       List<AccountItems> results= accRepo.bankStatus();


        return results;
    }

    @Override
    public Double liquidity() {
        return accRepo.otherAccounts()-accRepo.loanAccount();
    }

    @Override
    public List<Account> getAllAccounts(Customer customer) {

        return accRepo.findAllByCustomer(customer);

    }

    @Override
    public void deposit(Long account, Double amount) {

    }

    @Override
    public Account findByAcc(Long account) {
        return accRepo.findAccountsByAccountNumberEquals(account);
    }
}
