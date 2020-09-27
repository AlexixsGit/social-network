package com.social.service;

import com.social.model.Like;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikeService {
    Flux<Like> findAll();

    Mono<Like> save(Like like);

    Flux<Like> findByUserId(String userId);

    Flux<Like> findByProductId(String productId);

}
