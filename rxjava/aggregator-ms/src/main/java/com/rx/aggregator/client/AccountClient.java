package com.rx.aggregator.client;

import com.rx.aggregator.model.AccountDto;
import com.rx.aggregator.model.TransactionDto;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class AccountClient {

    @Autowired(required = true)
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Observable<AccountDto> getAccounts(int customerId) {
        List<AccountDto> accountDtos = restTemplate.exchange("http://localhost:8090/api/account/" + customerId, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AccountDto>>() {
                }).getBody();
        Observable<AccountDto> observableAccounts = Observable.fromIterable(accountDtos);
        return observableAccounts;
    }

    public Observable<TransactionDto> getTransactions(int customerId, int accountId) {
        List<TransactionDto> transactionDtos = restTemplate.exchange("http://localhost:8090/api/account/" + customerId + "/" + accountId, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TransactionDto>>() {
                }).getBody();
        Observable<TransactionDto> observableTransactions = Observable.fromIterable(transactionDtos);
        return observableTransactions;
    }
}
