package com.social.repository;

import com.social.model.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface LikeRepository extends ReactiveMongoRepository<Like, String> {
    Flux<Like> findByUserId(String userId);

    Flux<Like> findByProductId(String productId);

}
