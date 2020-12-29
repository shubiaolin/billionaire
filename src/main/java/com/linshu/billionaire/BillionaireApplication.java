package com.linshu.billionaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.linshu.billionaire.mapper")
public class BillionaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillionaireApplication.class, args);
    }

}
