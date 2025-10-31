package com.fhir.demo.dataparser.dataparser;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fhir.demo.dataparser.dataparser.kafka.FhirMessageConsumer;
import com.fhir.demo.dataparser.dataparser.kafka.KafkaMessageHandler;
import com.fhir.demo.dataparser.dataparser.kafka.KafkaMessageHandlerImpl;
import com.fhir.demo.dataparser.dataparser.services.HandleCBCImpl;
import com.fhir.demo.dataparser.dataparser.shared.MessageHelper;

@SpringBootApplication
public class DataParserApplication {
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
