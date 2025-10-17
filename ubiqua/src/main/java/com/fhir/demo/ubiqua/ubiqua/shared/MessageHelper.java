package com.fhir.demo.ubiqua.ubiqua.shared;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.json.simple.JSONObject;

/**
 * The type Message helper.
 */
public class MessageHelper {

    private static Properties props;

    /**
     * Gets random string.
     *
     * @return the random string
     */
    public static String getRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * Gets message log entry json.
     *
     * @param source  the source
     * @param topic   the topic
     * @param key     the key
     * @param message the message
     * @return the message log entry json
     * @throws Exception the exception
     */
    public static JSONObject getMessageLogEntryJSON(String source, String topic, String key, String message) throws Exception {
        JSONObject obj = new JSONObject();
        String bootstrapServers = getProperties().getProperty("bootstrap.servers");
        obj.put("bootstrapServers", bootstrapServers);
        obj.put("source", source);
        obj.put("topic", topic);
        obj.put("key", key);
        obj.put("message", message);

        return obj;
    }

    /**
     * Gets simple json object.
     *
     * @param message the message
     * @return the simple json object
     * @throws Exception the exception
     */
    public static JSONObject getSimpleJSONObject(String message) throws Exception {
        JSONObject obj = new JSONObject();
        String bootstrapServers = getProperties().getProperty("bootstrap.servers");
        obj.put("message", message);
        return obj;
    }

    public static Properties getProperties() throws Exception {

        Properties props = null;
        //try to load the file config.properties
        try (InputStream input = KafkaProducer.class.getClassLoader().getResourceAsStream("application.properties")) {

            props = new Properties();

            if (input == null) {
                throw new Exception("Sorry, unable to find application.properties");
            }

            //load a properties file from class path, inside static method
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return props;
    }
}
