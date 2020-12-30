package com.linshu.billionaire.service;

import com.linshu.billionaire.entity.SsqEntity;

import java.util.List;

public interface SsqService {
    List<SsqEntity> selectAllList();

    int getMaxNumId();

    SsqEntity selectById(int id);
}
