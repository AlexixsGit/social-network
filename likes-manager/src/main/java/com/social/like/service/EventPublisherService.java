package com.social.like.service;

import com.social.common.event.LikeEvent;
import com.social.like.config.RabbitMQConfig;
import com.social.like.model.Like;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EventPublisherService {
    private final RabbitTemplate template;

    public void publishLikeEvent(Like like) {
        LikeEvent event = LikeEvent.builder()
                .productId(like.getProductId())
                .userId(like.getUserId())
                .status(like.getStatus())
                .build();

        String json = event.toJSON();
        String msgId = UUID.randomUUID().toString();
        String correlationId = like.getId();

        publish(msgId, correlationId, json);
    }

    private void publish(final String msgId, final String correlationId, final String json) {
        MessageProperties msgProps = new MessageProperties();
        msgProps.setMessageId(msgId);
        msgProps.setCorrelationId(correlationId);
        msgProps.setContentType("application/json");

        byte[] evtMsgBytes = json.getBytes();

        Message message = new Message(evtMsgBytes, msgProps);
        template.send(RabbitMQConfig.TOPIC_EXCHANGE, "like.event." + msgId, message);
    }
}
