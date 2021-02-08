package com.linshu.billionaire.service.impl;

import com.linshu.billionaire.entity.NextBallEntity;
import com.linshu.billionaire.entity.SsqEntity;
import com.linshu.billionaire.mapper.SsqMapper;
import com.linshu.billionaire.service.NextBallService;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.linshu.billionaire.util.Util.UtilTool;

@Service("nextBallService")
public class NextBallServiceImpl implements NextBallService {

    private final Integer numberMap[][] = {
        {10, 20, 30},
        {1, 11, 21, 31},
        {2, 12, 22, 32},
        {3, 13, 23, 33},
        {4, 14, 24},
        {5, 15, 25},
        {6, 16, 26},
        {7, 17, 27},
        {8, 18, 28},
        {9, 19, 29},
    };

    @Autowired
    private SsqMapper ssqMapper;

    @Override
    public List<NextBallEntity> calculateNormalRecommend() {
        List<SsqEntity> ssqList = ssqMapper.selectListBetweenNumId(20127,21006);
        List<NextBallEntity> nextBallList = new ArrayList<NextBallEntity>();

        ssqList.stream().forEach((ssq -> {
            if(ssq.getIsDownload() == 1) {
                NextBallEntity nextBall = new NextBallEntity();
                nextBall.setNumId(ssq.getNumId());
                nextBall.setRed1(ssq.getRed1());
                nextBall.setRed2(ssq.getRed2());
                nextBall.setRed3(ssq.getRed3());
                nextBall.setRed4(ssq.getRed4());
                nextBall.setRed5(ssq.getRed5());
                nextBall.setRed6(ssq.getRed6());

                int sum = nextBall.getRedSum();
                List<Integer> onesPlace = new ArrayList<Integer>();
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed1()));
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed2()));
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed3()));
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed4()));
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed5()));
                onesPlace.add(UtilTool.getOnesPlace(sum, nextBall.getRed6()));


                nextBall.setOnesPlace(onesPlace.stream().distinct().collect(Collectors.toList()));
                List<Integer> allRecommend = new ArrayList<Integer>();
                nextBall.getOnesPlace().stream().forEach(one -> {
                    Integer[] a = numberMap[one];
                    Collections.addAll(allRecommend,a);
                });
                nextBall.setNormalRecommend(allRecommend);

                nextBallList.add(nextBall);
                System.out.println(nextBall);
            }
        }));

        return null;
    }

}
