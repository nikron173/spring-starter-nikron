package com.nikron.spring.dto;

import com.nikron.spring.database.entity.Company;
import com.nikron.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    String image;
    CompanyReadDto company;
}
