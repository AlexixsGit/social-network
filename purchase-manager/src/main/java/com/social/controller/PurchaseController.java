package com.social.controller;

import com.social.model.Purchase;
import com.social.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/purchase")
    public Mono<Purchase> save(@RequestBody Purchase purchase) {
        return this.purchaseService.save(purchase);
    }

    @GetMapping("/purchase")
    public Flux<Purchase> findAll() {
        return this.purchaseService.findAll();
    }
}
