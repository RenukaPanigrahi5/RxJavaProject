package com.rx.account.controller;

import com.rx.account.model.AccountDto;
import com.rx.account.model.TransactionDto;
import io.reactivex.Observable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    @GetMapping("/{customerId}")
    public Observable<AccountDto> getAccounts(@PathVariable("customerId") final int customerId) {
        return Observable.range(0, customerId).
                map(integer -> new AccountDto(integer, UUID.randomUUID().toString(),
                        new Random().nextDouble()));
    }

    @GetMapping("/{customerId}/{accountId}")
    public Observable<TransactionDto> getTransactions(@PathVariable("customerId") final int customerId, @PathVariable("accountId") final int accountId) {
        return Observable.range(0, 50).map(integer ->
                new TransactionDto(
                        String.format("Customer=%d, Transaction=%d",
                                customerId, accountId, integer), new Random().nextDouble()));
    }
}
