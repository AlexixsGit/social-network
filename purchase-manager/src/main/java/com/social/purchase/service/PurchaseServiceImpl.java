package com.social.purchase.service;

import com.social.product.model.Product;
import com.social.purchase.model.Purchase;
import com.social.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private WebClient client = WebClient.builder().baseUrl("http://localhost:8081").build();
    private final PurchaseRepository purchaseRepository;
    private final EventPublisherService eventPublisherService;

    @Override
    public Mono<Purchase> save(Purchase purchase) {
        purchase.setId(UUID.randomUUID().toString());
        Product product = client.get().uri("/products/" + purchase.getProductId())
                .retrieve().bodyToMono(Product.class).block();
        purchase.setTotal(product.getPrice() * purchase.getQuantity());

        Mono<Purchase> saved = this.purchaseRepository.save(purchase);
        this.eventPublisherService.publishPurchaseEvent(saved.block());
        return saved;
    }

    @Override
    public Flux<Purchase> findAll() {
        return this.purchaseRepository.findAll();
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.purchaseRepository.deleteById(id);
    }
}
