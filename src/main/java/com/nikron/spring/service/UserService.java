package com.nikron.spring.service;

import com.nikron.spring.annotation.bpp.InjectBean;
import com.nikron.spring.database.entity.User;
import com.nikron.spring.database.repository.UserRepository;
import com.nikron.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) return optionalUser.get();
        throw new RuntimeException("User with id " + id + " not found");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
