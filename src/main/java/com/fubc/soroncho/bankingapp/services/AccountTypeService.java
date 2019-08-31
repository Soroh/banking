package com.fubc.soroncho.bankingapp.services;

import com.fubc.soroncho.bankingapp.model.AccountType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountTypeService {

    AccountType findById(Long id);
    List<AccountType> findAll();

}
