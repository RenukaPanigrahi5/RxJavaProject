package com.rx.aggregator.client;

import com.rx.aggregator.model.AccountDto;
import com.rx.aggregator.model.TransactionDto;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;


@Component
public class AccountClient {
    public Observable<AccountDto> getAccounts(int customerId) {
        return Observable.range(0, customerId).
                map(integer -> new AccountDto(integer, UUID.randomUUID().toString(),
                        new Random().nextDouble()));
    }

    public Observable<TransactionDto> getTransactions(int customerId, int accountId) {
        return Observable.range(0, 50).map(integer ->
                new TransactionDto(
                        String.format("Customer=%d, Transaction=%d",
                                customerId, accountId, integer), new Random().nextDouble()));
    }
}
