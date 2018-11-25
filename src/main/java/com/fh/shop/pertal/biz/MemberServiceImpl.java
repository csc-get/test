package com.fh.shop.pertal.biz;

import com.fh.shop.pertal.common.ServerResponse;
import com.fh.shop.pertal.util.SendHttpClientUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("memberService")
public class MemberServiceImpl implements IMemberService {

    @Value("${sms.verifyName.url}")
    private  String url;
    @Value("${sms.addMember.url}")
    private  String addurl;


    @Override
    public ServerResponse verificationUaerName(String userName) {
        Map<String,String> params = new HashMap<>();
        params.put("memberName",userName);
        String result = SendHttpClientUtil.sendGet(url, params, null);
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(result, ServerResponse.class);
        return  serverResponse;
    }

    @Override
    public ServerResponse addMember(Map member) {
        String result = SendHttpClientUtil.sendPost(addurl, member, null);
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(result, ServerResponse.class);
        return serverResponse;
    }
}
