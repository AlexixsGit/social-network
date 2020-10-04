package com.social.like.service;

import com.social.common.event.PurchaseEvent;
import com.social.like.model.Like;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PurchaseEventConsumerService {
    private final LikeService likeService;

    @RabbitListener(queues = "purchase.event.q")
    public void listenPurchaseEvent(Message in) {
        String message = new String(in.getBody(), StandardCharsets.UTF_8);
        log.debug("received message: {}", message);
        PurchaseEvent purchase = PurchaseEvent.fromJSON(message);

        if (purchase == null) {
            log.error("could not read purchase event");
            return;
        }
        Like like = new Like();
        like.setProductId(purchase.getProductId());
        like.setUserId(purchase.getUserId());
        like.setId(UUID.randomUUID().toString());
        this.likeService.save(like);
        log.info("Like created - id: {}", like.getId());
    }
}
