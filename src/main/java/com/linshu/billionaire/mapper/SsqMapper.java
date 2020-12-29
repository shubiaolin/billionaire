package com.linshu.billionaire.mapper;

import com.linshu.billionaire.entity.SsqEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SsqMapper {
    //TODO selectAllList
    //TODO selectById
    //TODO selectByConditions
    //TODO insert
    //TODO insertList
    //TODO update
    //TODO updateList
    //TODO delete
    //TODO deleteById
    //TODO deleteByConditions
    List<SsqEntity> findAll();
}
