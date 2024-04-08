package com.nikron.spring.mapper;

import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.entity.User;
import com.nikron.spring.database.repository.CompanyRepository;
import com.nikron.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;


    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUserName(object.getUsername());
        user.setFirstName(object.getFirstname());
        user.setLastName(object.getLastname());
        user.setRole(object.getRole());
        user.setBirthDate(object.getBirthDate());
        user.setCompany(getCompany(object.getCompanyId()));
        user.setPassword(object.getPassword());

        Optional.ofNullable(object.getImage()).filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
