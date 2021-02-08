package com.linshu.billionaire.service;

import com.linshu.billionaire.entity.NextBallEntity;

import java.util.List;

public interface NextBallService {
    List<NextBallEntity> calculateNormalRecommend();
}
