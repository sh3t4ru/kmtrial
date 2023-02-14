package com.example.kmtrial.service;


import com.example.kmtrial.model.UserEntity;
import com.example.kmtrial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private UserEntity stubUser;

    @Bean
    public UserEntity stubUser() {
        if (stubUser == null) {
            stubUser = new UserEntity();
            stubUser.setEmail("example@google.com");
            stubUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            stubUser.setName("admin");
            stubUser.setPassword("admin");
            stubUser = createUser(stubUser);
        }
        return stubUser;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(userEntity); // password is encrypted, we promise
    }
}
