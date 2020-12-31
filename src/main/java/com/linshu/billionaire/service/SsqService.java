package com.linshu.billionaire.service;

import com.linshu.billionaire.entity.SsqEntity;

import java.util.List;

public interface SsqService {
    List<SsqEntity> selectAllList();

    int getMaxNumId();

    int countTotalTurn();

    SsqEntity selectById(int id);

    List<SsqEntity> selectByBlueBall(int blue);

    int insert(SsqEntity entity);

    int insertBatch(List<SsqEntity> entities);
}
