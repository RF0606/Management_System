package com.peter.managementproject;

import com.peter.pojo.User;
import com.peter.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagementProjectApplicationTests {

    @Autowired
    userService userService;
    @Test
    void contextLoads() {
        User user = userService.queryUserByName("peterfang");
        System.out.println(user.getPassword());
    }
}
