package com.peter.service;

import com.peter.mapper.userMapper;
import com.peter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//是service层的实现类，所以要加service
@Service
public class userServiceImpl implements userService{
    @Autowired
    private userMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
