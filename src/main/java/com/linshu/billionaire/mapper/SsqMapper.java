package com.linshu.billionaire.mapper;

import com.linshu.billionaire.entity.SsqEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SsqMapper {
    List<SsqEntity> selectAllList();

    List<SsqEntity> selectListBetweenNumId(@Param("start") int start, @Param("end") int end);

    SsqEntity selectById(int id);

    Integer getMaxNumId();

    int countTotalTurn();

    List<SsqEntity> getTurnOnIsDownload(int isDownload);

    List<SsqEntity> selectByBlueBall(int blue);

    int insert(SsqEntity entity);

    int insertBatch(List<SsqEntity> entities);

    int update(SsqEntity entity);



//    //TODO 待处理
//    List<SsqEntity> selectByConditions(SsqEntity condition);
//
//    Boolean insert(SsqEntity entity);
//    Boolean insertList(List<SsqEntity> entity);
//
//    Boolean update(SsqEntity entity);
//    Boolean updateList(List<SsqEntity> entity);
//
//    Boolean deleteById(long id);
//    Boolean deleteByConditions(SsqEntity entity);
}
