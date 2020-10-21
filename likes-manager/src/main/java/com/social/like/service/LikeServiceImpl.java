package com.social.like.service;

import com.social.like.model.Like;
import com.social.like.model.LikeStatus;
import com.social.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final EventPublisherService eventPublisherService;

    @Override
    public Flux<Like> findAll() {
        return this.likeRepository.findAll();
    }

    @Override
    public Mono<Like> save(Like like) {
        like.setId(UUID.randomUUID().toString());
        like.setStatus(LikeStatus.CREATED);
        Mono<Like> saved = this.likeRepository.save(like);
        this.eventPublisherService.publishLikeEvent(Objects.requireNonNull(saved.block()));
        return saved;
    }

    @Override
    public Flux<Like> findByUserId(String userId) {
        return this.likeRepository.findByUserId(userId);
    }

    @Override
    public Flux<Like> findByProductId(String productId) {
        return this.likeRepository.findByProductId(productId);
    }

    @Override
    public Mono<Void> deleteById(String likeId) {
        Like likeDeleted = this.likeRepository.findById(likeId)
                .doOnNext(like -> like.setStatus(LikeStatus.DELETED))
                .block();

        Mono<Void> likeMonoDeleted = this.likeRepository.deleteById(likeId);

        this.eventPublisherService.publishLikeEvent(Objects.requireNonNull(likeDeleted));

        return likeMonoDeleted;
    }
}
