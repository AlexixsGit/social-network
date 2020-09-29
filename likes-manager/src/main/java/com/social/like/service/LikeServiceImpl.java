package com.social.like.service;

import com.social.like.repository.LikeRepository;
import com.social.like.model.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public Flux<Like> findAll() {
        return this.likeRepository.findAll();
    }

    @Override
    public Mono<Like> save(Like like) {
        return this.likeRepository.save(like);
    }

    @Override
    public Flux<Like> findByUserId(String userId) {
        return this.likeRepository.findByUserId(userId);
    }

    @Override
    public Flux<Like> findByProductId(String productId) {
        return this.likeRepository.findByProductId(productId);
    }
}
