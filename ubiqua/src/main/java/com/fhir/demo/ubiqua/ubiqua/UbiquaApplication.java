package com.fhir.demo.ubiqua.ubiqua;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fhir.demo.ubiqua.ubiqua.kafka.FhirMessageConsumer;
import com.fhir.demo.ubiqua.ubiqua.kafka.KafkaMessageHandler;
import com.fhir.demo.ubiqua.ubiqua.kafka.KafkaMessageHandlerImpl;
import com.fhir.demo.ubiqua.ubiqua.services.HandleCBCImpl;
import com.fhir.demo.ubiqua.ubiqua.shared.MessageHelper;

@SpringBootApplication
public class UbiquaApplication {
	private static class ApplicationMessageHandlerImpl implements KafkaMessageHandler{

        /**
         * The Log.
         */
        static Logger log = Logger.getLogger(ApplicationMessageHandlerImpl.class.getName());

        @Override
        public void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception {
            String source = KafkaMessageHandlerImpl.class.getName();
            JSONObject obj = MessageHelper.getMessageLogEntryJSON(source, topicName,message.key(),message.value());
            System.out.println(obj.toJSONString());
            new HandleCBCImpl().processCBC(message.value());
        }
    }


	public static void main(String[] args) throws Exception {
		new FhirMessageConsumer().runAlways("fhir", new ApplicationMessageHandlerImpl() );
	}

}
