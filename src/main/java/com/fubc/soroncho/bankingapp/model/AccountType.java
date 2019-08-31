package com.fubc.soroncho.bankingapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountType
{
    @Id@GeneratedValue
    private Long accountTypeId;
    private String accountTypeName;
    @OneToMany(mappedBy = "accountType")
    private List<Account> accountList = new ArrayList<>();

    public AccountType() {
    }

    public AccountType(String accountTypeName, List<Account> accountList) {
        this.accountTypeName = accountTypeName;
        this.accountList = accountList;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return
                 accountTypeName ;
    }
}
