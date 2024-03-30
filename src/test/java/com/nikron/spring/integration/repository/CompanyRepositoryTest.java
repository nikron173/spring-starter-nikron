package com.nikron.spring.integration.repository;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.database.entity.Company;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryTest {

    private final EntityManager entityManager;
    @Test
    void findById(){
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }
}
