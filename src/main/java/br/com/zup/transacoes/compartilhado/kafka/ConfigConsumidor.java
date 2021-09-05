package br.com.zup.transacoes.compartilhado.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigConsumidor {

    private final KafkaProperties kafkaProperties;

    public ConfigConsumidor(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    private final String groupId = "${spring.kafka.consumer.group-id}";

    public Map<String, Object> consumerConf() {

        Map<String, Object> propriedades = new HashMap<>();

        propriedades.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        propriedades.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        propriedades.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propriedades.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());
        propriedades.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return propriedades;
    }

    @Bean
    public ConsumerFactory<String, ResponseTransacao> transactionConsumerFactory() {

        var jsonDeserializer = new JsonDeserializer<>(ResponseTransacao.class, false);
        var stringDeserializer = new StringDeserializer();

        return new DefaultKafkaConsumerFactory<>(consumerConf(), stringDeserializer, jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ResponseTransacao> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, ResponseTransacao> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }
}