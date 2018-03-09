package com.andrewjdonley.functions.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import com.andrewjdonley.functions.domain.marshal.InstantMarshaller;
import com.andrewjdonley.functions.domain.serializer.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Data
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName="AmazingAggregate")
public class CustomerAggregate {
    @DynamoDBHashKey
    private String customerId;

    @DynamoDBAttribute
    @DynamoDBMarshalling(marshallerClass = InstantMarshaller.class)
    @JsonSerialize(using = InstantSerializer.class)
    private Instant created; // We could use the DynamoDB provided created annotation here

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;
}
