package com.fh.shop.pertal.biz;

import com.fh.shop.pertal.common.ServerResponse;

public interface ISmsSendService {

    ServerResponse smsSend(String mobile);

}
