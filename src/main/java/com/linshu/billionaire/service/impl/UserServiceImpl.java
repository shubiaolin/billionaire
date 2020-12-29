package com.linshu.billionaire.service.impl;

import com.linshu.billionaire.entity.User;
import com.linshu.billionaire.mapper.UserMapper;
import com.linshu.billionaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
