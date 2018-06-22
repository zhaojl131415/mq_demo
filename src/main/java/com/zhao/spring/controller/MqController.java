package com.zhao.spring.controller;

import com.zhao.spring.service.MqService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
//@EnableAutoConfiguration
@RequestMapping("/mq")
public class MqController {

    @Resource
    private MqService mqService;

    @PostMapping(value = "send_msg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getCodeByName(@RequestParam(required = false, value = "msg") String msg) {
        mqService.sendMsg(msg);
    }


}
