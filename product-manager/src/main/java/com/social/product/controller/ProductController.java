package com.social.product.controller;

import com.social.product.model.Product;
import com.social.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public Mono<Product> save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping("/products")
    public Flux<Product> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return this.productService.findById(id);
    }

    @DeleteMapping("/products/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.productService.deleteById(id);
    }

    @PatchMapping("/products/{id}")
    public Mono<Product> update(@PathVariable String id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }
}
