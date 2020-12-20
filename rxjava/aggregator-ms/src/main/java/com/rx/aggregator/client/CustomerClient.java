package com.rx.aggregator.client;

import com.rx.aggregator.model.CustomerDto;
import io.reactivex.Single;
import org.springframework.stereotype.Component;

@Component
public class CustomerClient {
    public Single<CustomerDto> getCustomer(int id){
        return Single.create(
                emitter -> {
                    if(id > 50){
                        emitter.onError(new RuntimeException("Not a valid Customer"));
                    }else {
                        emitter.onSuccess(new CustomerDto(id,"First-" +id,"Last-" +id));
                    }
                }
        );
    }
}
