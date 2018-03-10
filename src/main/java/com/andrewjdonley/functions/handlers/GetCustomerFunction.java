package com.andrewjdonley.functions.handlers;

import com.andrewjdonley.functions.domain.CustomerAggregate;
import com.andrewjdonley.functions.domain.GenericResponse;
import com.andrewjdonley.functions.exception.NotFoundException;
import com.andrewjdonley.functions.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.function.Function;

@Component
public class GetCustomerFunction implements Function<String, GenericResponse<CustomerAggregate>> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final CustomerService customerService;

    @Autowired
    public GetCustomerFunction(
            final CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @Override
    public GenericResponse<CustomerAggregate> apply(@PathVariable String customerId) {
        return new GenericResponse<>(this.customerService.get(customerId)
                .orElseThrow(() -> new NotFoundException("Could not find customer with id: " + customerId)));
    }
}
