package com.social.purchase.service;

import com.social.purchase.model.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseService {
    Mono<Purchase> save(Purchase purchase);

    Flux<Purchase> findAll();

    Mono<Void> delete(String id);

}
