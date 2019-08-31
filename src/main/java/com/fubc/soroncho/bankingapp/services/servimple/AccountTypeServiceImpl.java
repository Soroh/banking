package com.fubc.soroncho.bankingapp.services.servimple;


import com.fubc.soroncho.bankingapp.model.AccountType;
import com.fubc.soroncho.bankingapp.repositories.AccountTypeRepository;
import com.fubc.soroncho.bankingapp.services.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    private AccountTypeRepository accountTypeRepository;


    @Override
    public AccountType findById(Long id) {
        return accountTypeRepository.findById(id).orElse(new AccountType());
    }

    @Override
    public List<AccountType> findAll() {
        return accountTypeRepository.findAll();
    }
}
