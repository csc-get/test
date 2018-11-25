package com.fh.shop.pertal.biz;

import com.fh.shop.pertal.common.ServerResponse;
import com.fh.shop.pertal.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

    @Value("${sms.arer.url}")
    private String url;

    public ServerResponse findAraeList(Integer id) {
        Map<String ,String > map=new HashMap<>();
        map.put("aid",id+"");
        ServerResponse serverResponse = HttpClientUtil.httpGet(url, map, null);
        return serverResponse;
    }
}
