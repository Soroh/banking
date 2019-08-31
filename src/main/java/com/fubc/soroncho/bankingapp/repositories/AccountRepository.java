package com.fubc.soroncho.bankingapp.repositories;

import com.fubc.soroncho.bankingapp.model.Account;
import com.fubc.soroncho.bankingapp.model.AggregateResults;
import com.fubc.soroncho.bankingapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
 @Query(value = "SELECT account_type_name as accountName, count(DISTINCT account_id) as numberOfAccounts,min(balance) as MinimumBalance,round(avg(balance),2) as averageBalance,max(balance)as  maximumBalance,sum(balance) as subTotal \n" +
            "FROM fubc_bank.account inner join account_type on account_type_id= account_type_id_fk group by account_type_name",nativeQuery = true)
    List<AccountItems> bankStatus();
 @Query(value = "SELECT sum(a.balance) as subTotal FROM fubc_bank.account a where account_type_id_fk != 2",nativeQuery = true)
    Double otherAccounts();
 @Query(value = "SELECT sum(a.balance) as subTotal FROM fubc_bank.account a where account_type_id_fk = 2",nativeQuery = true) Double loanAccount();
    List<Account> findAllByCustomer(Customer customer);

    Account findAccountsByAccountNumberEquals(Long account);
}
