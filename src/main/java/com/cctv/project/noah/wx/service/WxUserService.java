package com.cctv.project.noah.wx.service;

import com.cctv.project.noah.wx.entity.WxUser;

import java.util.List;

/**
 * 微信用户管理Service接口
 * 
 * @author yanhao
 * @date 2019-12-09
 */
public interface WxUserService{
    /**
     * 查询微信用户管理
     * 
     * @param userId 微信用户管理ID
     * @return 微信用户管理
     */
    public WxUser selectWxUserById(Long userId);

    /**
     * 查询微信用户管理列表
     * 
     * @param wxUser 微信用户管理
     * @return 微信用户管理集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增微信用户管理
     * 
     * @param wxUser 微信用户管理
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改微信用户管理
     * 
     * @param wxUser 微信用户管理
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除微信用户管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxUserByIds(String ids);

    /**
     * 删除微信用户管理信息
     * 
     * @param userId 微信用户管理ID
     * @return 结果
     */
    public int deleteWxUserById(Long userId);

    int getAllWxUser();
}
