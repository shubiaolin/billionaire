package com.linshu.billionaire.download.util;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.nodes.Document;

public class FetchUrlResources {
    public Document fetchUrl(String url) {
        String outRes = "";
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("【"+url+"】访问成功");
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "gbk"));
                String res = "";
                while ((res = buffReader.readLine()) != null) {
                    outRes += res;
                }
                buffReader.close();
            } else {
                System.err.println("【"+url+"】访问失败");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return Jsoup.parse(outRes); // 使用jsoup 进行语言转换
    }
}
