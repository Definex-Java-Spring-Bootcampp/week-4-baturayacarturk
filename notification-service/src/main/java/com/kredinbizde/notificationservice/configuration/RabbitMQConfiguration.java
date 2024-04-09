package com.kredinbizde.notificationservice.configuration;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfiguration  {
    public static final String TOPIC_EXCHANGE_NAME = "myTopicExchange";

    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitMQConfiguration(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }
    @Bean
    public Queue notifyQueue() {
        return new Queue("notify");
    }
    @Bean
    public Binding notifyServiceBinding(TopicExchange topicExchange, Queue notifyQueue) {
        return BindingBuilder.bind(notifyQueue).to(topicExchange).with("notify");
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter){
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}