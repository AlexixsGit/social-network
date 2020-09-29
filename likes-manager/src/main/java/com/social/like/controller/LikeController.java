package com.social.like.controller;

import com.social.like.service.LikeService;
import com.social.like.model.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes")
    public Mono<Like> save(@RequestBody Like like) {
        return this.likeService.save(like);
    }

    @GetMapping("/likes")
    public Flux<Like> findAll() {
        return this.likeService.findAll();
    }

    @GetMapping("/likes/find-by-user-id/{userId}")
    public Flux<Like> findByUserId(@PathVariable String userId) {
        return this.likeService.findByUserId(userId);
    }

    @GetMapping("/likes/find-by-product-id/{productId}")
    public Flux<Like> findByProductId(@PathVariable String productId) {
        return this.likeService.findByProductId(productId);
    }
}
