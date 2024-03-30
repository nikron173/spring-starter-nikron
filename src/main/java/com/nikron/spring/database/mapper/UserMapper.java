package com.nikron.spring.database.mapper;

import com.nikron.spring.database.entity.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public List<User> toEntities(ResultSet rs) {
        List<User> users = new ArrayList<>();
//        try {
//            while (rs.next()) {
//                users.add(new User(rs.getLong("id"), rs.getString("username")));
//            }
//            return users;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return users;
    }
}
