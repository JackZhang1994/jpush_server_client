package com.jvtd.bop2.service;

import com.jvtd.bop2.entity.PushAliasReqBean;
import com.jvtd.bop2.entity.BasePushReqBean;
import com.jvtd.bop2.entity.RegistrationIdReq;
import com.jvtd.bop2.service.iface.JPushServiceService;
import com.jvtd.bop2.util.JPushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class JPushServiceServiceImp implements JPushServiceService {

    private static final Logger log = LoggerFactory.getLogger(JPushServiceServiceImp.class);

    /**
     * 处理给指定别名的用户推送
     *
     * @param reqBean 请求实体类，内部包含一个或多个别名
     */
    @Override
    public boolean handleSendPush2Alias(PushAliasReqBean reqBean) {
        return JPushUtil.sendPush2Alias(reqBean);
    }

    /**
     * 处理给所有用户的推送
     *
     * @param reqBean
     */
    @Override
    public boolean handleSendPush(BasePushReqBean reqBean) {
        return JPushUtil.handleSendPush(reqBean);
    }

    /**
     * 通过RegistrationId获取设备的别名和标签信息
     * @param reqBean
     * @return
     */
    @Override
    public boolean getInfoByRegistrationId(RegistrationIdReq reqBean) {
        return JPushUtil.getInfoByRegistrationId(reqBean);
    }
}
