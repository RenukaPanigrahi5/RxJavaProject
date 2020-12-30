package com.rx.aggregator.client;

import com.rx.aggregator.model.AccountDto;
import com.rx.aggregator.model.TransactionDto;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class AccountClient {

    @Autowired(required = true)
    private RestTemplate restTemplate;

    private final String ACCOUNT_BASE_URL = "http://localhost:8090/api/account/";

    public Observable<AccountDto> getAccounts(int customerId) {
        List<AccountDto> accountDtos = restTemplate.exchange(ACCOUNT_BASE_URL + customerId, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }).getBody();
        Observable<AccountDto> observableAccounts = Observable.fromIterable(accountDtos);
        return observableAccounts;
    }

    public Observable<TransactionDto> getTransactions(int customerId, int accountId) {
        List<TransactionDto> transactionDtos = restTemplate.exchange(ACCOUNT_BASE_URL + customerId + "/" + accountId, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TransactionDto>>() {
                }).getBody();
        Observable<TransactionDto> observableTransactions = Observable.fromIterable(transactionDtos);
        return observableTransactions;
    }

}
