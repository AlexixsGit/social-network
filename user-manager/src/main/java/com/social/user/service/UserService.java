package com.social.user.service;

import com.social.user.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> save(User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<Void> delete(String id);

    Mono<User> update(String id, User user);

    Flux<User> getFriends(String id);
}
