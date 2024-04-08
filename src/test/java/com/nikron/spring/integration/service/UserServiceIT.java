package com.nikron.spring.integration.service;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.database.entity.Role;
import com.nikron.spring.dto.UserCreateEditDto;
import com.nikron.spring.dto.UserReadDto;
import com.nikron.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {

    private final UserService userService;
    private final static Long USER_1 = 1L;
    private final static Integer COMPANY_1 = 1;


    @Test
    void findById() {
        var user = userService.findById(USER_1);
        assertTrue(user.isPresent());
        user.ifPresent(u -> assertEquals("ivan@gmail.com", u.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto dto = new UserCreateEditDto(
                "test@jj.com",
                LocalDate.of(1997, 8, 30),
                "Test",
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        UserReadDto actualResult = userService.create(dto);

        assertEquals(dto.getUsername(), actualResult.getUsername());
        assertEquals(dto.getFirstname(), actualResult.getFirstname());
        assertEquals(dto.getLastname(), actualResult.getLastname());
        assertEquals(dto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(dto.getCompanyId(), actualResult.getCompany().getId());
        assertSame(dto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto dto = new UserCreateEditDto(
                "test@jj.com",
                LocalDate.of(1997, 8, 30),
                "Test",
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, dto);

        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(actual -> {
            assertEquals(dto.getUsername(), actual.getUsername());
            assertEquals(dto.getFirstname(), actual.getFirstname());
            assertEquals(dto.getLastname(), actual.getLastname());
            assertEquals(dto.getBirthDate(), actual.getBirthDate());
            assertEquals(dto.getCompanyId(), actual.getCompany().getId());
            assertSame(dto.getRole(), actual.getRole());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(-999L));
    }
}
