package com.nikron.spring.integration.http.controller;

import com.nikron.spring.annotation.IT;
import com.nikron.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
    void findAll() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", IsCollectionWithSize.hasSize(3)));
    }

    @Test
    @SneakyThrows
    void create() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                .param(UserCreateEditDto.Fields.username, "test@jj.com")
                .param(UserCreateEditDto.Fields.firstname, "Test")
                .param(UserCreateEditDto.Fields.lastname, "Test")
                .param(UserCreateEditDto.Fields.role, "USER")
                .param(UserCreateEditDto.Fields.birthDate, "30-08-1997")
                .param(UserCreateEditDto.Fields.companyId, "1")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/api/v1/users/{\\d+}")
        );
    }
}
