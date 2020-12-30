package com.rx.aggregator.client;

import com.rx.aggregator.model.CustomerDto;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerClient {

    @Autowired(required = true)
    private RestTemplate restTemplate;

    public Single<CustomerDto> getCustomer(int customerId) {
        CustomerDto customerDtos = restTemplate.exchange("http://localhost:8091/api/customers/" + customerId, HttpMethod.GET, null,
                CustomerDto.class).getBody();
        Single<CustomerDto> singleDtos = Single.just(customerDtos);
        return singleDtos;
    }
}
