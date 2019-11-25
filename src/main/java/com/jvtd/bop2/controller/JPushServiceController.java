package com.jvtd.bop2.controller;


import com.alibaba.fastjson.JSONObject;
import com.jvtd.bop2.constant.ResponseConstant;
import com.jvtd.bop2.entity.PushAliasReqBean;
import com.jvtd.bop2.entity.BasePushReqBean;
import com.jvtd.bop2.entity.RegistrationIdReq;
import com.jvtd.bop2.entity.ResponseBean;
import com.jvtd.bop2.service.iface.JPushServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpush")
public class JPushServiceController {

    private static final Logger log = LoggerFactory.getLogger(JPushServiceController.class);

    @Autowired
    private JPushServiceService jPushService;

    /**
     * 给指定Alias的用户发送推送
     *
     * @param reqBean 请求参数，内部包含Alias集合
     * @return
     */
    @RequestMapping("/sendPush2Alias")
    public ResponseBean sendPush2Alias(@RequestBody PushAliasReqBean reqBean) {
        log.info("sendPush2Alias， 请求参数：{}", JSONObject.toJSONString(reqBean));
        try {
            if (reqBean != null && reqBean.getTargetAlias() != null && !reqBean.getTargetAlias().isEmpty()) {
                boolean success = jPushService.handleSendPush2Alias(reqBean);
                return new ResponseBean(success ? ResponseConstant.SUCCESS : ResponseConstant.FAILED);
            } else {
                return new ResponseBean(ResponseConstant.FAILED);
            }
        } catch (Exception e) {
            return new ResponseBean(ResponseConstant.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 给所有用户发送推送
     *
     * @param reqBean 请求参数，内部包含Alias集合
     * @return
     */
    @RequestMapping("/sendPush")
    public ResponseBean sendPush(@RequestBody BasePushReqBean reqBean) {
        log.info("sendPush， 请求参数：{}", JSONObject.toJSONString(reqBean));
        try {
            boolean success = jPushService.handleSendPush(reqBean);
            return new ResponseBean(success ? ResponseConstant.SUCCESS : ResponseConstant.FAILED);
        } catch (Exception e) {
            return new ResponseBean(ResponseConstant.SYSTEM_EXCEPTION);
        }
    }

    /**
     * 通过RegistrationId获取设备的别名和标签信息
     * @return
     */
    @RequestMapping("/getInfoByRegistrationId")
    public ResponseBean getInfoByRegistrationId(@RequestBody RegistrationIdReq reqBean) {
        log.info("getInfoByRegistrationId， 请求参数：{}", JSONObject.toJSONString(reqBean));
        try {
            boolean success = jPushService.getInfoByRegistrationId(reqBean);
            return new ResponseBean(success ? ResponseConstant.SUCCESS : ResponseConstant.FAILED);
        } catch (Exception e) {
            return new ResponseBean(ResponseConstant.SYSTEM_EXCEPTION);
        }
    }
}
