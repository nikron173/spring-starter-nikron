package com.nikron.spring.service;

import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.repository.CompanyRepository;
import com.nikron.spring.dto.CompanyReadDto;
import com.nikron.spring.listener.AccessType;
import com.nikron.spring.listener.EntityEvent;
import com.nikron.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CompanyReadMapper companyReadMapper;

    public Company findById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) return optionalCompany.get();
        throw new RuntimeException("Company with id " + id + " not found");
    }

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map).toList();
    }
}
