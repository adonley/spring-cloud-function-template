package com.andrewjdonley.functions.config;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
public class DynamoConfig {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final String serviceEndpoint;

    public DynamoConfig(
            @Value("${aws.dynamoDB.serviceEndpoint}") String serviceEndpoint
    ) {
        this.serviceEndpoint = serviceEndpoint;
    }

    @Bean
    @Profile({"default", "compose"})
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.US_EAST_1));
        client.setEndpoint(serviceEndpoint);
        return client;
    }

    @Bean
    @Profile({"prod"})
    public AmazonDynamoDB productionAmazonDynamoDB() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.US_EAST_1));
        return client;
    }

    @Bean
    public DynamoDB productionDynamoDB(final AmazonDynamoDB client) {
        return new DynamoDB(client);
    }
}
