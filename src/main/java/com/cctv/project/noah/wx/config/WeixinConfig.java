package com.cctv.project.noah.wx.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author by yanhao
 * @Classname WeixinConfig
 * @Description TODO
 * @Date 2019/11/14 4:10 下午
 */
@Configuration
public class WeixinConfig {

    @Value("${wechat.app-id}")
    private String appId;

    @Value("${wechat.app-secret}")
    private String appSecret;

    @Value("${wechat.token}")
    private String token;



    @Bean
    public WxMpDefaultConfigImpl wxCpDefaultConfig(){
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        // 设置微信公众号的appid
        config.setAppId(appId);
        // 设置微信公众号的app corpSecret
        config.setSecret(appSecret);
        // 设置微信公众号的token
        config.setToken(token);
        // 设置微信公众号的EncodingAESKey
        config.setAesKey("");
        return config;
    }

    @Bean
    public WxMpService wxMpService(WxMpDefaultConfigImpl wxMpDefaultConfig){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpDefaultConfig);
        return wxMpService;
    }


}
