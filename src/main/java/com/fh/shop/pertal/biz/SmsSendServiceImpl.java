package com.fh.shop.pertal.biz;

import com.fh.shop.pertal.common.ServerResponse;
import com.fh.shop.pertal.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("smsSendService")
public class SmsSendServiceImpl implements ISmsSendService {

    @Value("${sms.url}")
    private  String  smsUrl;

    @Override
    public ServerResponse smsSend(String mobile) {
        Map<String,String> map=new HashMap<>();
        map.put("mobile",mobile);
        ServerResponse serverResponse1 = HttpClientUtil.httpGet(smsUrl,map, null);
        return serverResponse1;
    }
}
