package com.social.service;

import com.social.model.User;
import com.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> save(User user) {
        return this.userRepository.save(user);
    }
}
