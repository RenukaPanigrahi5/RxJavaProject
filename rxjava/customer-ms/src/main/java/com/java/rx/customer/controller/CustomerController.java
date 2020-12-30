package com.java.rx.customer.controller;

import com.java.rx.customer.model.CustomerDto;
import io.reactivex.Single;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping("/{customerId}")
    public Single<CustomerDto> getCustomer(@PathVariable("customerId") int customerId) {
        return Single.create(
                emitter -> {
                    if (customerId > 50) {
                        emitter.onError(new RuntimeException("Not a valid Customer"));
                    } else {
                        emitter.onSuccess(new CustomerDto(customerId, "First-" + customerId, "Last-" + customerId));
                    }
                }
        );
    }
}
