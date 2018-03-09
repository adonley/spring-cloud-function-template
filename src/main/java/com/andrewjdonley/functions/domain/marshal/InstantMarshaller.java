package com.andrewjdonley.functions.domain.marshal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;

import java.time.Instant;

public class InstantMarshaller implements DynamoDBMarshaller<Instant> {
    @Override
    public String marshall(Instant getterReturnResult) {
        return Long.toString(getterReturnResult.toEpochMilli());
    }

    @Override
    public Instant unmarshall(Class<Instant> clazz, String obj) {
        return Instant.ofEpochMilli(Long.valueOf(obj));
    }
}
