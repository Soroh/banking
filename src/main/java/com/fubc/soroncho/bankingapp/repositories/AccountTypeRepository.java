package com.fubc.soroncho.bankingapp.repositories;

import com.fubc.soroncho.bankingapp.model.AccountType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {
}
