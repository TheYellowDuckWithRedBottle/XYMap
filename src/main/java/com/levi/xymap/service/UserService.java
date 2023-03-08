package com.levi.xymap.service;

import com.levi.xymap.entity.User;
import com.levi.xymap.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
     int save(User user);
     List<User> findAll();
}
