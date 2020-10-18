package com.social.product.service;

import com.social.common.event.LikeEvent;
import com.social.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
@Slf4j
public class LikeEventConsumerService {
    private final ProductService productService;

    @RabbitListener(queues = "like.event.q")
    public void listenLikeEvent(Message in) {
        String message = new String(in.getBody(), StandardCharsets.UTF_8);
        log.debug("received message: {}", message);
        LikeEvent like = LikeEvent.fromJSON(message);

        if (like == null) {
            log.error("could not read like event");
            return;
        }

        //Raise likes counter of a product
        Mono<Product> productMono = this.productService
                .findById(like.getProductId()).doOnNext(prod ->
                        prod.setLikesCounter(prod.getLikesCounter() + 1))
                .flatMap(this.productService::save);
        productMono.subscribe();
    }
}
