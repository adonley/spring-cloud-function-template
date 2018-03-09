package com.andrewjdonley.functions.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import com.andrewjdonley.functions.domain.CustomerAggregate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This is an example repository.
 * This DB pattern is not the best but I'm using it in this example.
 */
@Repository
public class CustomerRepository {
    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public CustomerRepository(
            final AmazonDynamoDB amazonDynamoDB
    ) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = new DynamoDBMapper(this.amazonDynamoDB);

        // CreateTable if not exists, bad pattern to have in this class.
        CreateTableRequest tableRequest = this.dynamoDBMapper.generateCreateTableRequest(CustomerAggregate.class);
        List<String> tableNames = this.amazonDynamoDB.listTables().getTableNames();
        if(!tableNames.contains(tableRequest.getTableName())) {
            ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(200l, 200l);
            tableRequest.setProvisionedThroughput(provisionedThroughput);
            this.amazonDynamoDB.createTable(tableRequest);
        }
    }

    public void save(CustomerAggregate customerAggregate) {
        this.dynamoDBMapper.save(customerAggregate);
    }

    public Optional<CustomerAggregate> get(String customerId) {
        CustomerAggregate customerAggregate =
                this.dynamoDBMapper.load(CustomerAggregate.class,
                        customerId,
                        new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
        return Optional.ofNullable(customerAggregate);
    }
}
