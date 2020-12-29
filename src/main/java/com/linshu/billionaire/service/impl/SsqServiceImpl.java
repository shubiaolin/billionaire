package com.linshu.billionaire.service.impl;

import com.linshu.billionaire.entity.SsqEntity;
import com.linshu.billionaire.mapper.SsqMapper;
import com.linshu.billionaire.service.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class SsqServiceImpl implements SsqService {
    @Autowired
    private SsqMapper userMapper;

    @Override
    public List<SsqEntity> findAll() {
        return userMapper.findAll();
    }
}
