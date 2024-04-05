package com.nikron.spring.mapper;

import com.nikron.spring.database.entity.User;
import com.nikron.spring.dto.CompanyReadDto;
import com.nikron.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;
    @Override
    public UserReadDto map(User object) {
        CompanyReadDto company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);
        return new UserReadDto(
                object.getId(),
                object.getUserName(),
                object.getBirthDate(),
                object.getFirstName(),
                object.getLastName(),
                object.getRole(),
                object.getImage(),
                company
        );
    }
}
