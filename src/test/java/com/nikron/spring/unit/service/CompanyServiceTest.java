package com.nikron.spring.unit.service;

import com.nikron.spring.annotation.bpp.InjectBean;
import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.repository.CompanyRepository;
import com.nikron.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CompanyService companyService;

    private static final Long COMPANY_ID = 1L;

    @Test
    void findById() {
//        Mockito.doReturn(Optional.of(new Company(COMPANY_ID, "Apple")))
//                .when(companyRepository).findById(COMPANY_ID);
//
//        var actualResult = companyService.findById(COMPANY_ID);
//
//        assertNotNull(actualResult);
    }
}