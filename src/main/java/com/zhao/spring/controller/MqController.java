package com.zhao.spring.controller;

import com.zhao.spring.service.MqService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
//@EnableAutoConfiguration
@RequestMapping("/mq")
public class MqController {

    @Resource
    private MqService mqService;

    @GetMapping(value = "send_msg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getCodeByName(@RequestParam(required = false, value = "msg") String msg) {
        mqService.sendMsg(msg);
    }


}
