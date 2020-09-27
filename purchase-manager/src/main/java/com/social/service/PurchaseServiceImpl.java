package com.social.service;

import com.social.model.Product;
import com.social.model.Purchase;
import com.social.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private WebClient client = WebClient.builder().baseUrl("https://localhost:8081").build();
    private final PurchaseRepository purchaseRepository;

    @Override
    public Mono<Purchase> save(Purchase purchase) {

        Mono<Product> productMono = client.get().uri("/products/" + purchase.getProductId())
                .retrieve().bodyToMono(Product.class);

        AtomicReference<Double> total = new AtomicReference<>(0d);
        productMono.doOnNext(prod ->
                total.set(prod.getPrice() * purchase.getQuantity()));
        purchase.setTotal(total.get());
        return this.purchaseRepository.save(purchase);
    }

    @Override
    public Flux<Purchase> findAll() {
        return this.purchaseRepository.findAll();
    }
}
