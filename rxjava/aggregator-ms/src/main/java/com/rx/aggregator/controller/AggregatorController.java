package com.rx.aggregator.controller;

import com.rx.aggregator.client.AccountClient;
import com.rx.aggregator.client.CustomerClient;
import com.rx.aggregator.model.AccountDto;
import com.rx.aggregator.model.CustomerDto;
import com.rx.aggregator.model.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/customers")
public class AggregatorController {

    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private AccountClient accountClient;

    @GetMapping("/{customerId}")
    public CustomerDto getAllCustomerInfo(@PathVariable("customerId") final String customerId) {
        //use CustomerClient and AccountClient actually to get data.
        // Attempt1
        /*return this.customerClient.
                getCustomer(Integer.parseInt(customerId))
                .flatMap(customer -> {
                    return accountClient.getAccounts(customer.getId()).toList()
                            .map(accounts -> {
                                CustomerDto cust = new CustomerDto();
                                cust.setAccounts(accounts);
                                return cust;
                            });
                }).flatMap(
                        customerDto -> {
                            return Observable.fromIterable(customerDto.getAccounts())
                                    .map(
                                            account -> accountClient.getTransactions(customerDto.getId(), account.getId())
                                    ).toList()
                                    .map(transactions -> {
                                        // for each account set the transaction
                                        //each customer set the account + transaction
                                        AccountDto account1 = new AccountDto();
                                        account1.setTransactions(account1.getTransactions());
                                        CustomerDto customers =  new CustomerDto();
                                        customers.setAccounts(transactions);
                                        return customers;
                                    });
                        }
                ).blockingGet();*/
        //Attempt2 -- Working Code
        return this.customerClient
                .getCustomer(Integer.parseInt(customerId))
                .flatMap(customer -> {
                            return accountClient.getAccounts(customer.getId())
                                    .flatMap(
                                            account -> {
                                                return this.accountClient.getTransactions(Integer.parseInt(customerId), account.getId())
                                                        .toList().map(
                                                                txnList -> {
                                                                    account.setTransactions(txnList);
                                                                    return account;
                                                                }
                                                        ).toObservable();
                                            }
                                    ).toList()
                                    .map(accountList -> {
                                        customer.setAccounts(accountList);
                                        return customer;
                                    });
                        }

                ).blockingGet();


    }

    public CustomerDto sampleData() {
        return new CustomerDto(
                1, "Renuka", "Panigrahi",
                Arrays.asList(
                        new AccountDto(
                                1, "123", 200.12
                        ).withTransactions(
                                new TransactionDto("Dining", 12.50),
                                new TransactionDto("Drinking", 150.25)
                        ),
                        new AccountDto(
                                2, "456", 200.12
                        ).withTransactions(
                                new TransactionDto("Dining", 100.50),
                                new TransactionDto("Drinking", 400.50)
                        )
                )
        );
    }
}
