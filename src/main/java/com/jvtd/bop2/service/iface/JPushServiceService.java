package com.jvtd.bop2.service.iface;


import com.jvtd.bop2.entity.PushAliasReqBean;
import com.jvtd.bop2.entity.BasePushReqBean;
import com.jvtd.bop2.entity.RegistrationIdReq;

public interface JPushServiceService {
	/**
	 * 处理给指定别名的用户推送
	 * @param reqBean
	 */
	boolean handleSendPush2Alias(PushAliasReqBean reqBean);

	/**
	 * 处理给所有用户的推送
	 * @param reqBean
	 */
	boolean handleSendPush(BasePushReqBean reqBean);

	/**
	 * 通过RegistrationId获取设备的别名和标签信息
	 * @param reqBean
	 * @return
	 */
	boolean getInfoByRegistrationId(RegistrationIdReq reqBean);
}
