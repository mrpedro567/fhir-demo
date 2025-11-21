package com.fhir.demo.dataparser.dataparser.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fhir.demo.dataparser.dataparser.shared.MessageHelper;

public class KafkaMessageHandlerImpl implements KafkaMessageHandler {
    static Logger log = LoggerFactory.getLogger(KafkaMessageHandlerImpl.class);

    @Override
    public void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception {
        String position = message.partition() + "-" + message.offset();
        String source = KafkaMessageHandlerImpl.class.getName();
        JSONObject obj = MessageHelper.getMessageLogEntryJSON(source, topicName,message.key(),message.value());
        log.info(obj.toJSONString());
    }
    
}
