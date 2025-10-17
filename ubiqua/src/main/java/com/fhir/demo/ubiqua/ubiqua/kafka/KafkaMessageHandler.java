package com.fhir.demo.ubiqua.ubiqua.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaMessageHandler {
    /**
     * The method that defines the message processing behavior
     *
     * @param topicName The name of the topic being consumed
     * @param message   The message that was consumed
     * @throws Exception Thrown if an exception occurs
     */
    void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception;
}
