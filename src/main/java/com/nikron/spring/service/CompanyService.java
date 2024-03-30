package com.nikron.spring.service;

import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.repository.CompanyRepository;
import com.nikron.spring.listener.AccessType;
import com.nikron.spring.listener.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Company findById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) return optionalCompany.get();
        throw new RuntimeException("Company with id " + id + " not found");
    }

    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll();
        applicationEventPublisher.publishEvent(new EntityEvent(companies, AccessType.DELETE));
        return companies;
    }
}
