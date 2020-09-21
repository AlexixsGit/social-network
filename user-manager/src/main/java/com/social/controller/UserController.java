package com.social.controller;

import com.social.model.User;
import com.social.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public Mono<User> save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("/users/{id}")
    public Mono<User> findById(@PathVariable String id) {
        return this.userService.findById(id);
    }

    @GetMapping("/users")
    public Flux<User> findAll() {
        return this.userService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.userService.delete(id);
    }

    @PatchMapping("/users/{id}")
    public Mono<User> update(@PathVariable String id, @RequestBody User user) {
        return this.userService.update(id, user);
    }

}
