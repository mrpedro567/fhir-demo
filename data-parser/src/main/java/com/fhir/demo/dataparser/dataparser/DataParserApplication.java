package com.fhir.demo.dataparser.dataparser;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fhir.demo.dataparser.dataparser.kafka.FhirMessageConsumer;
import com.fhir.demo.dataparser.dataparser.kafka.KafkaMessageHandler;
import com.fhir.demo.dataparser.dataparser.kafka.KafkaMessageHandlerImpl;
import com.fhir.demo.dataparser.dataparser.services.HandleCBC;
import com.fhir.demo.dataparser.dataparser.services.HandleCBCImpl;
import com.fhir.demo.dataparser.dataparser.shared.MessageHelper;

@SpringBootApplication
public class DataParserApplication {
	private static class ApplicationMessageHandlerImpl implements KafkaMessageHandler{

        /**
         * The Log.
         */
        static Logger log = LoggerFactory.getLogger(ApplicationMessageHandlerImpl.class);
        static ApplicationContext ctx;

        ApplicationMessageHandlerImpl(ApplicationContext context){
            ctx = context;
        }

        @Override
        public void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception {
            String source = KafkaMessageHandlerImpl.class.getName();
            JSONObject obj = MessageHelper.getMessageLogEntryJSON(source, topicName,message.key(),message.value());
            System.out.println(obj.toJSONString());
            ctx.getBean(HandleCBCImpl.class).processCBC(message.value());
        }
    }


	public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(DataParserApplication.class, args);
		new FhirMessageConsumer().runAlways("fhir", new ApplicationMessageHandlerImpl(ctx) );
	}

}
