package com.linshu.billionaire.util;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class Util {
    public static Util UtilTool;

    private static int fullLength = 5;

    @PostConstruct
    public void init() {
        this.UtilTool = this;
    }

    public String fullTurn(int turn) {
        int curLength =  (turn + "").length();
        if(curLength == fullLength){
            return turn + "";
        } else {
            return ("00000" + turn).substring(curLength);
        }
    }

    public String getCurrentDate() {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }
}
