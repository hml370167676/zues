package com.hml.atp.zues.utils;

import com.hml.atp.zues.config.MQSenderConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hanminglu
 */
@Component
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDelay(String queue, Object t, Integer sec) {
        rabbitTemplate.convertAndSend(MQSenderConfig.EXCHANGE_DELAY, queue, t, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(sec * 1000);
                return message;
            }
        });
    }

    public void send(String routingKey, Object object) {
        rabbitTemplate.convertAndSend(routingKey, object);
    }

    public void sendTopic(String routingKey, Object object) {
        rabbitTemplate.convertAndSend("amq.topic", routingKey, object);
    }
}
