package com.fubc.soroncho.bankingapp.repositories;

public interface AccountItems {
    public String getAccountName();
    public Long getNumberOfAccounts();
    public Double getMinimumBalance();
    public Double getAverageBalance();
    public Double getMaximumBalance();
    public Double getSubtotal();
}
