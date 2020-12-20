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
    public CustomerDto getAllCustomerInfo(@PathVariable("customerId") String customerId) {
        //use CustomerClient and AccountClient actually to get data.
        return sampleData();
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
