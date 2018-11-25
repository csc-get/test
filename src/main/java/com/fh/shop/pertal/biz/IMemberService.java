package com.fh.shop.pertal.biz;

import com.fh.shop.pertal.common.ServerResponse;

import java.util.Map;

public interface IMemberService {
    ServerResponse verificationUaerName(String userName);

    ServerResponse addMember(Map member);
}
