package com.cctv.project.noah.wx.entity;

import com.cctv.project.noah.system.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cctv.project.noah.system.annotation.Excel;
import com.cctv.project.noah.system.core.domain.BaseEntity;
import java.util.Date;

/**
 * 微信用户管理对象 wx_user
 * 
 * @author yanhao
 * @date 2019-12-09
 */
public class WxUser extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 订阅该公众 */
    private Integer subscribe;

    /** openId */
    @Excel(name = "openId")
    private String openid;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 性别 */
    @Excel(name = "性别")
    private Integer sex;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 国家 */
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 语言 */
    private String language;

    /** 头像 */
    @Excel(name = "头像")
    private String headimgurl;

    /** 关注时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "关注时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subscribeTime;

    /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
    private String unionid;

    /** 分组 */
    @Excel(name = "分组")
    private String groupid;

    /** 标签 */
    @Excel(name = "标签")
    private String tagidList;

    /** 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_ LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他 */
    private String subscribeScene;

    /** 二维码扫码场景（开发者自定义） */
    private String qrScene;

    /** 二维码扫码场景描述（开发者自定义） */
    private String qrSceneStr;

    /** 最后登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getSubscribe() {
        return subscribe;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUnionid() {
        return unionid;
    }
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getGroupid() {
        return groupid;
    }
    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    public String getTagidList() {
        return tagidList;
    }
    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }
    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrScene() {
        return qrScene;
    }
    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("subscribe", getSubscribe())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("sex", getSex())
            .append("city", getCity())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("language", getLanguage())
            .append("headimgurl", getHeadimgurl())
            .append("subscribeTime", getSubscribeTime())
            .append("unionid", getUnionid())
            .append("remark", getRemark())
            .append("groupid", getGroupid())
            .append("tagidList", getTagidList())
            .append("subscribeScene", getSubscribeScene())
            .append("qrScene", getQrScene())
            .append("qrSceneStr", getQrSceneStr())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }

    public WxUser() {
    }

    public WxUser(WxMpUser userWxInfo) {
        if (userWxInfo != null) {
            Date nowDate = new Date();
            this.setUserId(null);
            this.setNickname(userWxInfo.getNickname());
            //是否关注公众号
            this.setSubscribe(userWxInfo.getSubscribe() == true ? 1 : 0);
            //性别
            this.setSex(userWxInfo.getSex());
            //openId
            this.setOpenid(userWxInfo.getOpenId());
            //城市
            this.setCity(userWxInfo.getCity());
            //国家
            this.setCountry(userWxInfo.getCountry());
            //省
            this.setProvince(userWxInfo.getProvince());
            //用户的语言
            this.setLanguage(userWxInfo.getLanguage());
            //用户头像
            this.setHeadimgurl(userWxInfo.getHeadImgUrl());
            //用户关注时间
            this.setSubscribeTime(new Date(userWxInfo.getSubscribeTime()));
            //unionid
            this.setUnionid(userWxInfo.getUnionId());
            //公众号运营者对粉丝的备注
            this.setRemark(userWxInfo.getRemark());
            //用户所在的分组ID
            this.setGroupid(userWxInfo.getGroupId().toString());
            //用户被打赏的标签ID列表
            this.setTagidList(StringUtils.join(userWxInfo.getTagIds(), ","));
            //二维码扫码场景
            this.setQrScene(userWxInfo.getQrScene());
            //二维码扫码场景描述
            this.setQrSceneStr(userWxInfo.getQrSceneStr());
            //最后登陆时间
            this.setLoginDate(nowDate);
            //创建者
            this.setCreateBy("关注");
            //创建时间
            this.setCreateTime(nowDate);
            //更新者
            this.setUpdateBy("关注");
            //更新时间
            this.setUpdateTime(nowDate);
        }
    }

    public WxUser(WxMpUser userWxInfo,String createName) {
        if (userWxInfo != null) {
            Date nowDate = new Date();
            this.setUserId(null);
            this.setNickname(userWxInfo.getNickname());
            //是否关注公众号
            this.setSubscribe(userWxInfo.getSubscribe() == true ? 1 : 0);
            //性别
            this.setSex(userWxInfo.getSex());
            //openId
            this.setOpenid(userWxInfo.getOpenId());
            //城市
            this.setCity(userWxInfo.getCity());
            //国家
            this.setCountry(userWxInfo.getCountry());
            //省
            this.setProvince(userWxInfo.getProvince());
            //用户的语言
            this.setLanguage(userWxInfo.getLanguage());
            //用户头像
            this.setHeadimgurl(userWxInfo.getHeadImgUrl());
            //用户关注时间
            this.setSubscribeTime(new Date(userWxInfo.getSubscribeTime()*1000));
            //unionid
            this.setUnionid(userWxInfo.getUnionId());
            //公众号运营者对粉丝的备注
            this.setRemark(userWxInfo.getRemark());
            //用户所在的分组ID
            this.setGroupid(userWxInfo.getGroupId().toString());
            //用户被打赏的标签ID列表
            this.setTagidList(StringUtils.join(userWxInfo.getTagIds(), ","));
            //二维码扫码场景
            this.setQrScene(userWxInfo.getQrScene());
            //二维码扫码场景描述
            this.setQrSceneStr(userWxInfo.getQrSceneStr());
            //最后登陆时间
            this.setLoginDate(nowDate);
            //创建者
            this.setCreateBy(createName);
            //创建时间
            this.setCreateTime(nowDate);
            //更新者
            this.setUpdateBy(createName);
            //更新时间
            this.setUpdateTime(nowDate);
            //关注
            this.setSubscribeScene(userWxInfo.getSubscribeScene());
        }
    }
}
