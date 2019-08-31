package com.fubc.soroncho.bankingapp.repositories;

import com.fubc.soroncho.bankingapp.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
  Page<Customer> findCustomerByFirstNameContainsOrLastNameContainsOrMiddleNameContainsOrEmailAddressContains(String fn, String ln, String mn, String email, Pageable tName);


}
