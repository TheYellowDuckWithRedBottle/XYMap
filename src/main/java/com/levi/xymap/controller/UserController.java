package com.levi.xymap.controller;

import com.levi.xymap.entity.User;
import com.levi.xymap.entity.mapper.UserMapper;
import com.levi.xymap.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userServiceImple;

    @PostMapping
    public Integer save(@RequestBody User user){
       return userServiceImple.save(user);
    }
    @GetMapping
    public List<User> index(){
        return userMapper.findAll();
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }
}
