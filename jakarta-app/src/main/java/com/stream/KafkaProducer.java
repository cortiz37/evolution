package com.stream;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducer {

    public static void sendMessage(String key, String message) {
        final Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        final String topic = "notifications";

        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

        producer.send(new ProducerRecord<>(topic, key, message), (m, e) -> {
            if (e != null) {
                e.printStackTrace();
            } else {
                System.out.printf("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(), m.partition(), m.offset());
            }
        });

        producer.flush();
        producer.close();
    }
}
