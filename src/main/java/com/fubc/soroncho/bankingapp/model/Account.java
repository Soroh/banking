package com.fubc.soroncho.bankingapp.model;

import javax.persistence.*;

@Entity
//@NamedQuery(name = "Account.findTotal", query = "SELECT sum(balance) FROM Account p WHERE LOWER(p.lastName) = LOWER(?1)")
public class Account {
    @Id@GeneratedValue
    private Long accountId;
    private Long accountNumber;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "account_type_id_fk")
    private AccountType accountType;
    @ManyToOne
    @JoinColumn(name = "customer_id_fk")
    private Customer customer;
    @Transient
    private Long existingCustomerId;
    @Transient
    private Long accountTypeId;

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Account() {
    }

    public Account(Long accountNumber, Double balance, AccountType accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.customer = customer;

    }

    public Long getExistingCustomerId() {
        return existingCustomerId;
    }

    public void setExistingCustomerId(Long existingCustomerId) {
        this.existingCustomerId = existingCustomerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", customer=" + customer +
                ", existingCustomerId=" + existingCustomerId +
                ", accountTypeId=" + accountTypeId +
                '}';
    }
    public String niceString(){
        return "<table><tr><td><h4><b> Account Number</b>:</td><td>" + accountNumber +"</td></tr>"+
                "<tr><td><br><b>Balance</b>:</td><td>" + balance +"</td></tr>"+
                "<tr><td><br><b>Account Type</b>:</td><td>" + accountType +"<br></h4></td></tr></table>";
    }
}
