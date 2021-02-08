package com.linshu.billionaire.download.controller;

import com.linshu.billionaire.download.util.FetchUrlResources;
import com.linshu.billionaire.entity.SsqEntity;
import com.linshu.billionaire.service.NextBallService;
import com.linshu.billionaire.service.SsqService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.linshu.billionaire.util.Util.UtilTool;

@Component
public class Download500ResourcesController {

    public static Download500ResourcesController Download500;

    @PostConstruct
    public void init() {
        Download500 = this;
    }

    @Autowired
    private SsqService ssqService;

    @Autowired
    private NextBallService nextBallService;

    private String period = "20065";
    private String turnStr = "$turn$";
    private String ssqUrl = "https://kaijiang.500.com/shtml/ssq/$turn$.shtml";
    private String regEx = "/(^\\s*)|(\\s*$)";

    private Boolean isLoadingTurn = false;
    private Boolean isLoadingBall = false;

    public void setLoadingBall(Boolean loadingBall) {
        this.isLoadingBall = loadingBall;
    }

    public Boolean getLoadingBall() {
        return this.isLoadingBall;
    }

    public void setLoadingTurn(Boolean loadingTurn) {
        this.isLoadingTurn = loadingTurn;
    }

    public Boolean getLoadingTurn() {
        return this.isLoadingTurn;
    }

    private FetchUrlResources fetchUrlResources = new FetchUrlResources();

    public void getTurn() {
//        System.out.println(ssqService.getMaxNumId());
//        System.out.println(ssqService.selectAllList());
//        System.out.println(ssqService.selectById(6).toString());
//        printlnSsqEntity(ssqService.selectByBlueBall(5));
//        System.out.println(ssqService.countTotalTurn());

        this.setLoadingTurn(true);
        String dbPeriod = ssqService.getMaxNumId() + "";
        System.out.println("当前数据库最新期数为："+dbPeriod);

        Document doc = fetchUrlResources.fetchUrl(this.ssqUrl.replace(this.turnStr, this.period));
        Elements turnElements = doc.select("div.iSelectList a");
        List<String> turns = turnElements.stream().sorted(Comparator.comparing(Element::text)).map(Element::text).collect(Collectors.toList());
        System.out.println("当前资源最新期数为："+turns.get(turns.size()-1));
        if(turns.size() > 0){
            List<String> newTurns = turns.subList(turns.indexOf(dbPeriod)+1,turns.size());
            for (String turn : newTurns) {
                SsqEntity entity = new SsqEntity();
                entity.setNumId(Integer.parseInt(turn));
                ssqService.insert(entity);
                System.out.println("【"+turn+"】期数入库!");
            }
            System.out.println("期数程序跑完");
//            System.out.println(ssqService.insertBatch(list));//TODO: 批量有问题，需要看看，先用单个的
        }
        this.setLoadingTurn(false);
    }

    public void reGetBallByDbTurns() {
        List<SsqEntity> SsqEntities = ssqService.getTurnOnIsDownload(0);
        if(this.getLoadingBall()){
            System.out.println("下载资源中，等待下个任务。");
        } else if (SsqEntities.size() == 0){
            System.out.println("暂无需要下载的资源。");
        } else {
            this.setLoadingBall(true);

            SsqEntities.stream().forEach((SsqEntity entity)->{
                this.getBall(entity);
            });
//            this.getBall(SsqEntities.get(0)); //测试用

            this.setLoadingBall(false);
        }
    }

    //根据期数获取
    private void getBall(SsqEntity entity) {
        Document doc = fetchUrlResources.fetchUrl(ssqUrl.replace( turnStr, UtilTool.fullTurn(entity.getNumId()) ));
        try {
            System.out.println("下载第【" + entity.getNumId() + "】");
            // 设置蓝球
            entity.setBlue(Integer.parseInt(doc.select(".ball_blue").text()));
            // 开奖号码
            Elements balls = doc.select("table table tbody tr td");

            //因为有些页面格式不一样
            int openIndex = 0, orderIIndex = 0;
            for(int index = 0; index < balls.size(); index++){
                if("开奖号码：".equals(balls.get(index).text())){
                    openIndex = index + 1;
                }
                if("出球顺序：".equals(balls.get(index).text())){
                    orderIIndex = index + 1;
                }
            }

            // 设置实际位序红球
            List<String> actualBall = Arrays.asList(balls.get(orderIIndex).text().replaceAll(regEx, "").split(" "));
            entity.setRed1(Integer.parseInt(actualBall.get(0)));
            entity.setRed2(Integer.parseInt(actualBall.get(1)));
            entity.setRed3(Integer.parseInt(actualBall.get(2)));
            entity.setRed4(Integer.parseInt(actualBall.get(3)));
            entity.setRed5(Integer.parseInt(actualBall.get(4)));
            entity.setRed6(Integer.parseInt(actualBall.get(5)));

            // 设置顺序位置红球
            List<String> orderBall = Arrays.asList(balls.get(openIndex).text().replaceAll(regEx, "").split(" "));
            entity.setRedOrder1(Integer.parseInt(orderBall.get(0)));
            entity.setRedOrder2(Integer.parseInt(orderBall.get(1)));
            entity.setRedOrder3(Integer.parseInt(orderBall.get(2)));
            entity.setRedOrder4(Integer.parseInt(orderBall.get(3)));
            entity.setRedOrder5(Integer.parseInt(orderBall.get(4)));
            entity.setRedOrder6(Integer.parseInt(orderBall.get(5)));

            // 设置开奖日期 TODO: 打算用正则，但是不熟就用土办法了
            String openDateStr = doc.select(".kj_tablelist02 .span_right").get(0).text();
            entity.setOpenDate(openDateStr.substring(5,openDateStr.indexOf(" 兑奖截止日期：")));

            // 设置更新时间（当前时间就行）
            entity.setUpdateTime(UtilTool.getCurrentDate());

            // 校验 所有字段是否有值后设置isDownload 是否完整下载
            entity.setIsDownload(entity.validate());

            System.out.println(entity);
            ssqService.update(entity);
        } catch(Exception e) {
            System.err.println(e.getClass());
        }
    }

    public void calculateRedBall() {
        nextBallService.calculateNormalRecommend();
    }

    private void printlnSsqEntity(List<SsqEntity> list) {
        System.out.println(list.size());
        for (SsqEntity ssqEntity : list) {
            System.out.println(ssqEntity.toString());
        }
    }
}
