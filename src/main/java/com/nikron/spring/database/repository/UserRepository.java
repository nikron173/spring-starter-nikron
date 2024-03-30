package com.nikron.spring.database.repository;

import com.nikron.spring.database.entity.User;
import com.nikron.spring.database.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<Long, User> {

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>();
    }
}
