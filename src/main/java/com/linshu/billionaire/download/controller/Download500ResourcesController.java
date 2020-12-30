package com.linshu.billionaire.download.controller;

import com.linshu.billionaire.download.util.FetchUrlResources;
import com.linshu.billionaire.service.SsqService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Download500ResourcesController {

    public static Download500ResourcesController Download500;

    @PostConstruct
    public void init() {
        Download500 = this;
    }

    @Autowired
    private SsqService ssqService;

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
//        System.out.println(ssqService.selectById(5).toString());

//        this.setLoadingTurn(true);
//        String dbPeriod = "20131"; //TODO 需要在这里获取数据库里面最大的期数，来对比
//        Document doc = fetchUrlResources.fetchUrl(this.ssqUrl.replace(this.turnStr, this.period));
//        Elements turnElements = doc.select("div.iSelectList a");
//        List<String> turns = turnElements.stream().sorted(Comparator.comparing(Element::text)).map(Element::text).collect(Collectors.toList());
//        if(turns.size() > 0){
//            this.getBall(turns.subList(turns.indexOf(dbPeriod),turns.size()));
//        }
//        this.setLoadingTurn(false);
    }

    public void reGetBallByDbTurns() {
        List<String> turns = new ArrayList<String>();//TODO 从数据库获取
        if(turns.size() > 0 && !this.getLoadingBall()) {
            this.getBall(turns);
        }
    }

    //根据期数获取
    private void getBall(List<String> turns) {
        this.setLoadingBall(true);
        turns.stream().forEach((String turn)->{
            Document doc = fetchUrlResources.fetchUrl(ssqUrl.replace(turnStr, turn));
            try {
                System.out.println(doc.select(".ball_blue").text());

                System.out.println(doc.select("table table tbody tr td").get(1).text().replaceAll(regEx, ""));
                System.out.println(doc.select("table table tbody tr td").get(3).text().replaceAll(regEx, ""));
            } catch(Exception e) {
                System.out.println(doc.text());
            }

        });
        this.setLoadingBall(false);
    }

}
