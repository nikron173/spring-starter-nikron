package com.nikron.spring.database.repository;

import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CompanyRepository implements Repository<Long, Company> {

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>();
    }
}
