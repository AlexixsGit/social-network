package com.social.service;

import com.social.model.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseService {
    Mono<Purchase> save(Purchase purchase);

    Flux<Purchase> findAll();

}
