package com.fh.shop.pertal.controller;

import com.fh.shop.pertal.biz.IAreaService;
import com.fh.shop.pertal.biz.IMemberService;
import com.fh.shop.pertal.biz.ISmsSendService;
import com.fh.shop.pertal.common.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class HttpController {
    @Resource(name = "smsSendService")
    private ISmsSendService smsSendService;
    @Resource(name = "memberService")
    private IMemberService memberService;
    @Resource(name = "areaService")
    private IAreaService areaService;

    @RequestMapping("/sms/smsSend")
    public ServerResponse smsSend(String mobile){
        return  smsSendService.smsSend(mobile);
    }

    @RequestMapping("/arer/findAraeList")
    public ServerResponse findAraeList(Integer id){
        return  areaService.findAraeList(id);
    }
    @RequestMapping("/member/verificationUaerName")
    public  ServerResponse verificationUaerName(String userName){
        return memberService.verificationUaerName(userName);
    }


    @RequestMapping("/member/addMember")
    public  ServerResponse addMember(@RequestParam Map member){
        return memberService.addMember(member);
    }
}
