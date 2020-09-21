package com.social.service;

import Util.Util;
import com.social.model.Product;
import com.social.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Mono<Product> save(Product product) {
        return this.productRepository.save(product);
    }

    public Flux<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Mono<Product> findById(String id) {
        return this.productRepository.findById(id);
    }

    public Mono<Void> deleteById(String id) {
        return this.productRepository.deleteById(id);
    }

    public Mono<Product> update(String id, Product product) {
        Mono<Product> foundProduct = findById(id);
        foundProduct = foundProduct.doOnNext(currentProduct ->
                BeanUtils.copyProperties(product, currentProduct, Util.getNullPropertyNames(product))
        );
        return foundProduct.flatMap(this::save);
    }
}
