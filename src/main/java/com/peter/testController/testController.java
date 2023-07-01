package com.peter.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class testController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList11")
    public List<Map<String, Object>> getUserInfo(){
        String sql = "SELECT * FROM logintable";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
