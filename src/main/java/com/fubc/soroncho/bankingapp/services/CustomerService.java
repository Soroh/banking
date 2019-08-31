package com.fubc.soroncho.bankingapp.services;

import com.fubc.soroncho.bankingapp.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Page<Customer> getAllCustomersPaged(int pageNo);

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer findById(Long customerId);

    Page<Customer> search(String search, int pageNo);

    List<Customer> getAllCustomers();
}
