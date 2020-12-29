package com.linshu.billionaire.controller;

import com.linshu.billionaire.entity.SsqEntity;
import com.linshu.billionaire.service.SsqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SsqController {

    @Autowired
    private SsqService userService;

    @RequestMapping("/findAll")
    public List<SsqEntity> findAll(){
        return userService.findAll();
    }
}
