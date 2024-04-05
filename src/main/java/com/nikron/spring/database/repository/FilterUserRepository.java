package com.nikron.spring.database.repository;

import com.nikron.spring.database.entity.User;
import com.nikron.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
