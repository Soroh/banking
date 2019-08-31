package com.fubc.soroncho.bankingapp.controllers;

import com.fubc.soroncho.bankingapp.model.Account;
import com.fubc.soroncho.bankingapp.model.AccountType;
import com.fubc.soroncho.bankingapp.model.AggregateResults;
import com.fubc.soroncho.bankingapp.model.Customer;
import com.fubc.soroncho.bankingapp.repositories.AccountItems;
import com.fubc.soroncho.bankingapp.services.AccountService;
import com.fubc.soroncho.bankingapp.services.AccountTypeService;
import com.fubc.soroncho.bankingapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {
    @Autowired private CustomerService customerService;
    @Autowired private AccountTypeService accountTypeService;
    @Autowired private AccountService accountService;

    @RequestMapping("/accounts/list")
    public ModelAndView accountList(){
        Page<Account> accounts = accountService.getAllAccountsPaged(0);
        List<AccountItems>  accountDetails = accountService.bankStatus();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts",accounts);
        modelAndView.addObject("accountDetails",accountDetails);
        modelAndView.addObject("liquidity",accountService.liquidity());
        modelAndView.setViewName("accounts/account-list");
        return modelAndView;
    }

    @RequestMapping("/accounts/add-account-to-existing-customer")
    public String newAccount(@RequestParam("customerId") Long customerId,Model model){
        Account account = new Account();
        account.setExistingCustomerId(customerId);
        List<AccountType> accountTypes = accountTypeService.findAll();
        model.addAttribute("accountTypes",accountTypes);
        model.addAttribute("account",account);
        return "accounts/new-account";
    }

    @RequestMapping("/account/save")
    public String saveAccount( @ModelAttribute Account account){
        account.setCustomer(customerService.findById(account.getExistingCustomerId()));
        account.setAccountType(accountTypeService.findById(account.getAccountTypeId()));
        accountService.saveAccount(account);
        return "redirect:/accounts/list";
    }

    @RequestMapping("/accounts/accounttype")
    public String newAccountType(){

        return "accounts/new-account-type";
    }
}
