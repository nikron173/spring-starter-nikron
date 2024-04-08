package com.nikron.spring.integration.http.controller;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
public class UserControllerIT {
    private final MockMvc mockMvc;

    @Test
    @SneakyThrows
    @WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "USER"})
    void findAll() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @SneakyThrows
    void create() {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/registration")
                .param(UserCreateEditDto.Fields.username, "test@jj.com")
                .param(UserCreateEditDto.Fields.firstname, "Test")
                .param(UserCreateEditDto.Fields.lastname, "Test")
                .param(UserCreateEditDto.Fields.role, "USER")
                .param(UserCreateEditDto.Fields.password, "Test")
                .param(UserCreateEditDto.Fields.birthDate, "30-08-1997")
                .param(UserCreateEditDto.Fields.companyId, "1")
                .param(UserCreateEditDto.Fields.image, "cat-1.jpg")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/login")
        );
    }
}
