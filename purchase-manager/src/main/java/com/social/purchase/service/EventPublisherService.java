package com.social.purchase.service;

import com.social.common.event.PurchaseEvent;
import com.social.purchase.config.RabbitMQConfig;
import com.social.purchase.model.Purchase;
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

    public void publishPurchaseEvent(Purchase purchase) {
        PurchaseEvent event = PurchaseEvent.builder()
                .productId(purchase.getProductId())
                .userId(purchase.getUserId())
                .quantity(purchase.getQuantity())
                .total(purchase.getTotal())
                .build();

        String json = event.toJSON();
        String msgId = UUID.randomUUID().toString();
        String correlationId = purchase.getId();

        publish(msgId, correlationId, json);
    }

    private void publish(final String msgId, final String correlationId, final String json) {
        MessageProperties msgProps = new MessageProperties();
        msgProps.setMessageId(msgId);
        msgProps.setCorrelationId(correlationId);
        msgProps.setContentType("application/json");

        byte[] evtMsgBytes = json.getBytes();

        Message message = new Message(evtMsgBytes, msgProps);
        template.send(RabbitMQConfig.TOPIC_EXCHANGE, "purchase.event." + msgId, message);
    }
}
