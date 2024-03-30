package com.nikron.spring.database.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<K, T> {
    Optional<T> findById(K id);
    List<T> findAll();
}
