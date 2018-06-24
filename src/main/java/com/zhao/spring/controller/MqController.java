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
    public void sendMsg(@RequestParam(required = false, value = "msg") String msg) {
        mqService.sendMsg(msg);
    }

    @GetMapping(value = "send_user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendUser() {
        mqService.sendUser();
    }

    @GetMapping(value = "send_message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendMessage(@RequestParam(required = false, value = "message") String message) {
        mqService.sendMessage(message);
    }

    @GetMapping(value = "send_error", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendError(@RequestParam(required = false, value = "error") String error) {
        mqService.sendError(error);
    }

    @GetMapping(value = "send_messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendMessages(@RequestParam(required = false, value = "messages") String messages) {
        mqService.sendMessages(messages);
    }

    @GetMapping(value = "send_fanout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sendFanout(@RequestParam(required = false, value = "fanout") String fanout) {
        mqService.sendFanout(fanout);
    }


}
