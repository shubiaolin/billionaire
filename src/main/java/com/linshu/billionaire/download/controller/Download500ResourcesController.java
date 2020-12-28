package com.linshu.billionaire.download.controller;

import com.linshu.billionaire.download.util.FetchUrlResources;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Download500ResourcesController {
    public String period = "20065";
    public String turnStr = "$turn$";
    public String ssqUrl = "https://kaijiang.500.com/shtml/ssq/$turn$.shtml";
    public String regEx = "/(^\\s*)|(\\s*$)";



    private FetchUrlResources fetchUrlResources = new FetchUrlResources();

    public void getTurn() {
        String dbPeriod = "20132"; //TODO 需要在这里获取数据库里面最大的期数，来对比
        if(Integer.parseInt(dbPeriod)>Integer.parseInt(period)){
            getTurn(dbPeriod);
        }
    }

    public void getTurn(String period) {
        Document doc = fetchUrlResources.fetchUrl(ssqUrl.replace(turnStr, period));
        Elements turnElements = doc.select("div.iSelectList a");
        List<String> turns = turnElements.stream().sorted(Comparator.comparing(Element::text)).map(Element::text).collect(Collectors.toList());
        Integer index = turns.indexOf(period);
        System.out.println(turns);
        if(turns.size() > 0){

        }
//        System.out.println(turns.subList(index,turns.size()));
    }

    public void getBall() {
        Document doc = fetchUrlResources.fetchUrl(ssqUrl.replace(turnStr, period));
        System.out.println(doc.select(".ball_blue").text());

        System.out.println(doc.select("table table tbody tr td").get(1).text().replaceAll(regEx, ""));
        System.out.println(doc.select("table table tbody tr td").get(3).text().replaceAll(regEx, ""));
    }

}
