package com.nikron.spring.database.repository;

import com.nikron.spring.database.entity.User;
import com.nikron.spring.dto.QPredicates;
import com.nikron.spring.dto.UserFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.nikron.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

//    @Override
//    public List<User> findAllByFilter(UserFilter filter) {
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//
//        var user = criteria.from(User.class);
//        criteria.select(user);
//        List<Predicate> predicates = new ArrayList<>();
//        if (Objects.nonNull(filter.firstname()) && !filter.firstname().isBlank()){
//            predicates.add(cb.like(user.get("firstName"), filter.firstname()));
//        }
//
//        if (Objects.nonNull(filter.lastname()) && !filter.lastname().isBlank()){
//            predicates.add(cb.like(user.get("lastName"), filter.lastname()));
//        }
//
//        if (Objects.nonNull(filter.birthDate())){
//            predicates.add(cb.lessThan(user.get("birthDate"), filter.birthDate()));
//        }
//
//        criteria.where(predicates.toArray(Predicate[]::new));
//
//        return entityManager.createQuery(criteria).getResultList();
//    }

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstName::containsIgnoreCase)
                .add(filter.lastname(), user.lastName::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
