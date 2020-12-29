package com.linshu.billionaire.mapper;

import com.linshu.billionaire.entity.SsqEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SsqMapper {
    List<SsqEntity> selectAllList();

    SsqEntity selectById();

    //TODO 待处理
    List<SsqEntity> selectByConditions(SsqEntity condition);

    Boolean insert(SsqEntity entity);
    Boolean insertList(List<SsqEntity> entity);

    Boolean update(SsqEntity entity);
    Boolean updateList(List<SsqEntity> entity);

    Boolean deleteById(long id);
    Boolean deleteByConditions(SsqEntity entity);
}
