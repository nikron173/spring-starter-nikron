package com.nikron.spring.integration.service;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    private static final Long COMPANY_ID = 1L;

    private final CompanyService companyService;

    @Test
    void findById(){
        var actualResult = companyService.findById(COMPANY_ID);

        assertNotNull(actualResult);
    }
}
