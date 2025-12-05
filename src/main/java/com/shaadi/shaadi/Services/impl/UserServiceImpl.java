package com.shaadi.shaadi.Services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shaadi.shaadi.Model.User;
import com.shaadi.shaadi.Repository.UserRepository;
import com.shaadi.shaadi.Services.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
