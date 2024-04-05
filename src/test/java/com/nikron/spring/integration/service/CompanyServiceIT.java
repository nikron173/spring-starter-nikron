package com.nikron.spring.integration.service;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.service.CompanyService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertThrows;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    private static final Long COMPANY_ID = 99L;

    private final CompanyService companyService;
    private final EntityManager entityManager;

    @Test
    void findById(){
        //assertThrows(RuntimeException.class, () -> companyService.findById(COMPANY_ID));
    }
}
