package com.fubc.soroncho.bankingapp.services.servimple;

import com.fubc.soroncho.bankingapp.model.Customer;
import com.fubc.soroncho.bankingapp.repositories.CustomerRepository;
import com.fubc.soroncho.bankingapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository cusRepo;


    @Override
    public Page<Customer> getAllCustomersPaged(int pageNo) {
        Page<Customer> customers = cusRepo.findAll(PageRequest.of(pageNo, 5, Sort.by("lastName")));
        return customers;
    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return cusRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        cusRepo.deleteById(customerId);

    }

    @Override
    public Customer findById(Long customerId) {
        return cusRepo.findById(customerId).orElse(new Customer());
    }

    @Override
    public Page<Customer> search(String search, int pageNo) {

        return
                cusRepo.findCustomerByFirstNameContainsOrLastNameContainsOrMiddleNameContainsOrEmailAddressContains(search,search,search,search,PageRequest.of(pageNo,5,Sort.by("lastName")));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return cusRepo.findAll();
    }
}
