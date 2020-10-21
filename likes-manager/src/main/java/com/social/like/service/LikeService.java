package com.social.like.service;

import com.social.like.model.Like;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LikeService {
    Flux<Like> findAll();

    Mono<Like> save(Like like);

    Flux<Like> findByUserId(String userId);

    Flux<Like> findByProductId(String productId);

    Mono<Void> deleteById(String likeId);
}
