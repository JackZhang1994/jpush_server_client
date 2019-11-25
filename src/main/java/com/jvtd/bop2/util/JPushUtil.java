package com.jvtd.bop2.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.jvtd.bop2.constant.Constant;
import com.jvtd.bop2.entity.BasePushReqBean;
import com.jvtd.bop2.entity.PushAliasReqBean;
import com.jvtd.bop2.entity.RegistrationIdReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.List;

public class JPushUtil {
    private static ClientConfig clientConfig = ClientConfig.getInstance();
    private static JPushClient jpushClient = new JPushClient(Constant.MASTER_SECRET, Constant.APP_KEY, null, clientConfig);

    private static final Logger log = LoggerFactory.getLogger(JPushUtil.class);

    /**
     * 发送带别名的推送
     */
    public static boolean sendPush2Alias(@NotNull PushAliasReqBean reqBean) {
        List<String> aliasList = reqBean.getTargetAlias();
        if (aliasList == null || aliasList.isEmpty()) {
            return false;
        }
        String[] aliasArray = reqBean.getTargetAlias().toArray(new String[aliasList.size()]);
        Audience audience = Audience.alias(aliasArray);
        return sendPush(reqBean, audience);
    }

    /**
     * 处理给所有用户的推送
     */
    public static boolean handleSendPush(BasePushReqBean reqBean) {
        return sendPush(reqBean, Audience.all());
    }

    /**
     * 真正发送推送
     *
     * @param reqBean  推送内容封装类
     * @param audience 推送范围
     * @return true:推送成功 false:推送失败
     */
    private static boolean sendPush(@NotNull BasePushReqBean reqBean, @NotNull Audience audience) {
        try {
            String title = reqBean.getTitle();
            String content = reqBean.getContent() == null ? "" : reqBean.getContent().length() > 25 ? reqBean.getContent().substring(0, 25) : reqBean.getContent();
            // True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。但注意，JPush 服务端 SDK 默认设置为推送 “开发环境”。该字段仅对 iOS 的 Notification 有效。
            boolean isApnsProduction = reqBean.isApnsProduction();
            // 推送当前用户不在线时，为该用户保留多长时间的离线消息，以便其上线时再次推送。默认 86400 （1 天），最长 10 天。设置为 0 表示不保留离线消息，只有推送当前在线的用户可以收到。该字段对 iOS 的 Notification 消息无效。
            long timeToAlive = reqBean.getTimeToAlive() == 0 ? 86400 : reqBean.getTimeToAlive();

            PushPayload pushPayload = PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(audience)
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(AndroidNotification.newBuilder()
                                    .setTitle(title)
                                    .setAlert(content)
                                    .addExtras(reqBean.getExtras())
                                    .build())
                            .addPlatformNotification(IosNotification.newBuilder()
                                    .setAlert(IosAlert.newBuilder()
                                            .setTitleAndBody(title, null, content)
                                            .build())
                                    .incrBadge(1)
                                    .addExtras(reqBean.getExtras())
                                    .build())
                            .build())
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(isApnsProduction)
                            .setTimeToLive(timeToAlive)
                            .build())
                    .build();
            PushResult pushResult = jpushClient.sendPush(pushPayload);
            return pushResult.statusCode == 0 || pushResult.statusCode == 200;
        } catch (APIConnectionException e) {
            e.printStackTrace();
            return false;
        } catch (APIRequestException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过RegistrationId获取设备的别名和标签信息
     * 注：测试使用
     *
     * @param reqBean
     * @return
     */
    public static boolean getInfoByRegistrationId(RegistrationIdReq reqBean) {
        try {
            TagAliasResult result = jpushClient.getDeviceTagAlias(reqBean.getRegistrationId());
            log.info("获取的设备信息为：" + result.toString());
            return true;
        } catch (APIConnectionException e) {
            e.printStackTrace();
            return false;
        } catch (APIRequestException e) {
            e.printStackTrace();
            return false;
        }
    }
}
