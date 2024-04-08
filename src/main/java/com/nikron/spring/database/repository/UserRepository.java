package com.nikron.spring.database.repository;

import com.nikron.spring.database.entity.User;
import com.nikron.spring.dto.PersonalInfo;
import com.nikron.spring.dto.UserFilter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    //@Query("select u from User u where u.company.id = :companyId")
    List<PersonalInfo> findAllByCompanyId(Integer companyId);

    List<User> findFirst2By(Sort sort);

    Optional<User> findByUserName(String username);

    @Query("select u from User u" +
            " where u.firstName like %:firstName% and u.lastName like %:lastName%")
    List<User> findUsersByFirstNameContainingAndLastNameContaining(String firstName, String lastName);

}
