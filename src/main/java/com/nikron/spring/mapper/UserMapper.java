package com.nikron.spring.mapper;

import com.nikron.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class UserMapper {
    private final UserDto userDto;
}
