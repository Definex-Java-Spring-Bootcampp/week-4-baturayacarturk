package com.kredinbizde.application.kredinbizdeapplication.configuration;

import org.springframework.amqp.core.*;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    public static final String TOPIC_EXCHANGE_NAME = "myTopicExchange";

    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }
    @Bean
    public Queue notifyServiceQueue() {
        return new Queue("notify");
    }

    @Bean
    public Queue akbankCreateQueue() {
        return new Queue("akbank.create");
    }
    @Bean
    public Queue akbankUpdateQueue() {
        return new Queue("akbank.update");
    }
    @Bean
    public Queue garantiCreateQueue() {
        return new Queue("garanti.create");
    }

    @Bean
    public Queue garantiUpdateQueue() {
        return new Queue("garanti.update");
    }
    @Bean
    public Queue cardQueue() {
        return new Queue("card");
    }
    @Bean
    public Queue loanQueue() {
        return new Queue("loan");
    }
    @Bean
    public Binding notifyServiceBinding(TopicExchange topicExchange, Queue notifyServiceQueue) {
        return BindingBuilder.bind(notifyServiceQueue).to(topicExchange).with("notify");
    }
    @Bean
    public Binding createLoanBinding(TopicExchange topicExchange, Queue loanQueue) {
        return BindingBuilder.bind(loanQueue).to(topicExchange).with("loan");
    }
    @Bean
    public Binding cardCreateBinding(TopicExchange topicExchange, Queue cardQueue) {
        return BindingBuilder.bind(cardQueue).to(topicExchange).with("card");
    }
    @Bean
    public Binding akbankCreateBinding(TopicExchange topicExchange, Queue akbankCreateQueue) {
        return BindingBuilder.bind(akbankCreateQueue).to(topicExchange).with("akbank.create");
    }

    @Bean
    public Binding akbankUpdateBinding(TopicExchange topicExchange, Queue akbankUpdateQueue) {
        return BindingBuilder.bind(akbankUpdateQueue).to(topicExchange).with("akbank.update");
    }
    @Bean
    public Binding garantiCreateBinding(TopicExchange topicExchange, Queue garantiCreateQueue) {
        return BindingBuilder.bind(garantiCreateQueue).to(topicExchange).with("garanti.create");
    }

    @Bean
    public Binding garantiUpdateBinding(TopicExchange topicExchange, Queue garantiUpdateQueue) {
        return BindingBuilder.bind(garantiUpdateQueue).to(topicExchange).with("garanti.update");
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
