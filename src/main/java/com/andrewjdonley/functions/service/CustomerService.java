package com.andrewjdonley.functions.service;

import com.andrewjdonley.functions.domain.CustomerAddRequest;
import com.andrewjdonley.functions.domain.CustomerAggregate;
import com.andrewjdonley.functions.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(
            final CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public CustomerAggregate addCustomer(CustomerAddRequest customerAddRequest) {
        CustomerAggregate customerAggregate = new CustomerAggregate();
        customerAggregate.setCustomerId(customerAddRequest.getCustomerId());
        customerAggregate.setFirstName(customerAddRequest.getFirstName());
        customerAggregate.setLastName(customerAddRequest.getFirstName());
        // This will actually overwrite the current created value if the customerId already exists. I'm lazy.
        customerAggregate.setCreated(Instant.now());
        this.customerRepository.save(customerAggregate);
        return customerAggregate;
    }

    public Optional<CustomerAggregate> get(String customerId) {
        return this.customerRepository.get(customerId);
    }
}
