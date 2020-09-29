package com.social.purchase.service;

import com.social.product.model.Product;
import com.social.purchase.model.Purchase;
import com.social.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private WebClient client = WebClient.builder().baseUrl("http://localhost:8081").build();
    private final PurchaseRepository purchaseRepository;

    @Override
    public Mono<Purchase> save(Purchase purchase) {

        Product product = client.get().uri("/products/" + purchase.getProductId())
                .retrieve().bodyToMono(Product.class).block();
        purchase.setTotal(product.getPrice() * purchase.getQuantity());
        return this.purchaseRepository.save(purchase);
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
