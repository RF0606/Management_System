package com.peter.service;

import com.peter.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


public interface userService {
    User queryUserByName(String name);
}
