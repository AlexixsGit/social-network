package com.social.service;

import Util.Util;
import com.social.model.User;
import com.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.userRepository.deleteById(id);
    }

    @Override
    public Mono<User> update(String id, User user) {
        Mono<User> foundUser = this.findById(id);
        foundUser = foundUser.doOnNext(currentUser ->
                BeanUtils.copyProperties(user, currentUser, Util.getNullPropertyNames(user)));
        return foundUser.flatMap(this::save);
    }

    @Override
    public Flux<User> getFriends(String id) {
        return this.userRepository.getByFriendId(id);
    }
}
