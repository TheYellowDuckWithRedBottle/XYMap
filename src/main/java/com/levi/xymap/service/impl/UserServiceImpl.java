package com.levi.xymap.service.impl;

import com.levi.xymap.entity.User;
import com.levi.xymap.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper userMapper;

    public int save(User user){
        if(user.getId() ==null){
           return userMapper.insert(user);
        } else {
           return userMapper.update(user);
        }
    }
}
