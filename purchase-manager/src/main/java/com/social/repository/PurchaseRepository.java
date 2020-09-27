package com.social.repository;

import com.social.model.Purchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PurchaseRepository extends ReactiveMongoRepository<Purchase, String> {
}
