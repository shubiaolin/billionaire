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
    private SsqMapper ssqMapper;

    @Override
    public List<SsqEntity> selectAllList() {
        return ssqMapper.selectAllList();
    }

    @Override
    public int getMaxNumId() {
        return ssqMapper.getMaxNumId();
    }

    @Override
    public SsqEntity selectById(int id) {
        return ssqMapper.selectById(id);
    }
}
