package com.nikron.spring.database.mapper;

import com.nikron.spring.database.entity.Company;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {
    public List<Company> toEntities(ResultSet rs) {
        List<Company> companies = new ArrayList<>();
//        try {
//            while (rs.next()) {
//                companies.add(new Company(rs.getLong("id"), rs.getString("name")));
//            }
//            return companies;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return companies;
    }
}
