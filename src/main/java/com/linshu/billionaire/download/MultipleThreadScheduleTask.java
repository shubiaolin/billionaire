package com.linshu.billionaire.download;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.linshu.billionaire.download.controller.Download500ResourcesController.Download500;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultipleThreadScheduleTask {
//    @Async
//    @Scheduled(fixedDelay = 3600000) //暂时每隔一个小时执行一次
    public void first() throws InterruptedException {
        Boolean loadingTurn = Download500.getLoadingTurn();

        if(!loadingTurn){
            Download500.getTurn();
        }
    }

//    @Async
//    @Scheduled(fixedDelay = 60000)
    public void downloadBall() {
        Boolean loadingTurn = Download500.getLoadingTurn();

        if(!loadingTurn){
            Download500.reGetBallByDbTurns();
        }
    }

    @Async
    @Scheduled(fixedDelay = 60000000)
    public void calculateRedBall() {
        Download500.calculateRedBall();
    }
}
