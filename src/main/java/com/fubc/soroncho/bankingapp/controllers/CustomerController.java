package com.fubc.soroncho.bankingapp.controllers;

import com.fubc.soroncho.bankingapp.model.Account;
import com.fubc.soroncho.bankingapp.model.AccountType;
import com.fubc.soroncho.bankingapp.model.Customer;
import com.fubc.soroncho.bankingapp.services.AccountService;
import com.fubc.soroncho.bankingapp.services.AccountTypeService;
import com.fubc.soroncho.bankingapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountTypeService accountTypeService;

    @RequestMapping("/customers/list")
    public ModelAndView customerList(@RequestParam(defaultValue = "0") int pageno){
        ModelAndView modelAndView = new ModelAndView();

        Page<Customer> customers = customerService.getAllCustomersPaged(pageno);
        long numberOfCustomers = customers.getTotalElements();
        customers.stream().forEach(x->{
            x.setAccounts(accountService.getAllAccounts(x));
            x.setAccountDetails("");
            StringBuilder s=new StringBuilder();
            x.getAccounts().stream().forEach(y->{
               s.append("\n"+y.niceString());
            });
            x.setAccountDetails(s.toString());
        });

        modelAndView.addObject("currPageNo", pageno);
        modelAndView.addObject("numberOfCustomers", numberOfCustomers);
        modelAndView.addObject("flashBack", "/customers/list");
        modelAndView.addObject("customers",customers);
        modelAndView.setViewName("customers/customer-list");
        return modelAndView;
    }





    @RequestMapping("/customer/search")
    public ModelAndView search(@RequestParam String search, @RequestParam(defaultValue = "0") int pageno) {
        Page<Customer> customers = customerService.search(search, pageno);
        long numberOfCustomers = customers.getTotalElements();
        ModelAndView modelAndView = new ModelAndView();

        customers.stream().forEach(x->{
            x.setAccounts(accountService.getAllAccounts(x));
            x.setAccountDetails("");
            StringBuilder s=new StringBuilder();
            x.getAccounts().stream().forEach(y->{
                s.append("\n"+y.niceString());
            });
            x.setAccountDetails(s.toString());
        });
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("currPageNo", pageno);
        modelAndView.addObject("numberOfCustomers", numberOfCustomers);
        modelAndView.addObject("flashBack", "/customer/search?search=" + search);
        modelAndView.setViewName("customers/customer-list");
        return modelAndView;
    }




    @RequestMapping("/customers/newCustomer")
    public String newAccount(@ModelAttribute Customer customer,Model model){
        List<AccountType> accountTypes = accountTypeService.findAll();
        model.addAttribute("customer",customer);
        model.addAttribute("accountTypes",accountTypes);
        return "customers/new-customer";
    }
    @RequestMapping("/customer/save")
    public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult result){
        if (result.hasErrors()) {
            return "customers/new-customer";
        }else {
            customer = customerService.saveCustomer(customer);
            AccountType accountType = accountTypeService.findById(customer.getAccountType());
            Account account = new Account(customer.getAccountNumber(), customer.getBalance(), accountType, customer);
            accountService.saveAccount(account);
            return "redirect:/customers/newCustomer";
        }

    }
    @RequestMapping("/customer/update-details")
    public String UpdateCustomerDetails( @ModelAttribute Customer customer){
        customer=customerService.saveCustomer(customer);
                return "redirect:/customers/list";

    }
    @RequestMapping("/customer/delete")
    public String deleteCustomer(@RequestParam("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
        return "redirect:/customers/list";
    }
    @RequestMapping("/customer/update")
    public String updateCustomer(@RequestParam("customerId") Long customerId,Model model){
        model.addAttribute(customerService.findById(customerId));
        return "customers/update-customer-details";
    }

    @RequestMapping("/customer/paypal")
    public String Dposit(@RequestParam("acc") Long account, @RequestParam("amount") Double amount){
        Account account1= accountService.findByAcc(account);
        account1.setBalance(account1.getBalance()+amount);
        accountService.saveAccount(account1);
        return "redirect:/customers/list";
    }

}
