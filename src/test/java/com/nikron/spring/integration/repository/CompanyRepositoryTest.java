package com.nikron.spring.integration.repository;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.repository.CompanyRepository;
import com.nikron.spring.database.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Test
    @Transactional
    void findById(){
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

//    @Test
//    @Transactional
//    void save() {
//        var company = Company.builder()
//                .name("Apple1")
//                .locales(Map.of(
//                        "ru", "Apple описание",
//                        "en", "Apple description"
//                ))
//                .build();
//        entityManager.persist(company);
//        assertNotNull(company.getId());
//    }

    @Test
    void delete() {
//        var maybeCompany = companyRepository.findById(5L);
//        assertTrue(maybeCompany.isPresent());
//        maybeCompany.ifPresent(companyRepository::delete);
//        entityManager.flush();
//        assertTrue(companyRepository.findById(5L).isEmpty());
    }

    @Test
    void findAllPersonalInfo(){
        var lst = userRepository.findAllByCompanyId(1);
        System.out.println(lst);
        assertThat(lst).hasSize(1);
    }

    @Test
    void findFirst2by(){
        var lst = userRepository.findFirst2By(Sort.by("id").descending());
        System.out.println(lst);
        assertThat(lst).hasSize(2);
    }
}
