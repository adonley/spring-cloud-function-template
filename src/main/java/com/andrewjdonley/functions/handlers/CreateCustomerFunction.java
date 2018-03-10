package com.andrewjdonley.functions.handlers;

import com.andrewjdonley.functions.domain.CustomerAddRequest;

import com.andrewjdonley.functions.domain.CustomerAggregate;
import com.andrewjdonley.functions.domain.GenericResponse;
import com.andrewjdonley.functions.exception.ConflictException;
import com.andrewjdonley.functions.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class CreateCustomerFunction implements Function<CustomerAddRequest, GenericResponse<CustomerAggregate>> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final CustomerService customerService;

    @Autowired
    public CreateCustomerFunction(
            final CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @Override
    public GenericResponse<CustomerAggregate> apply(CustomerAddRequest customerAddRequest) {
        this.customerService.get(customerAddRequest.getCustomerId()).ifPresent((customerAggregate) -> {
            throw new ConflictException("Customer with id: " + customerAggregate.getCustomerId() + " already in database");
        });
        return new GenericResponse<>(this.customerService.addCustomer(customerAddRequest));
    }
}
