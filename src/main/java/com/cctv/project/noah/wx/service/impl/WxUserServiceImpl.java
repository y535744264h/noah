package com.cctv.project.noah.wx.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.cctv.project.noah.system.util.DateUtils;
import com.cctv.project.noah.wx.entity.WxUser;
import com.cctv.project.noah.wx.service.WxService;
import com.cctv.project.noah.wx.service.WxUserService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cctv.project.noah.wx.mapper.WxUserMapper;
import com.cctv.project.noah.system.core.domain.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 微信用户管理Service业务层处理
 * 
 * @author yanhao
 * @date 2019-12-09
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private WxService wxService;

    /**
     * 查询微信用户管理
     * 
     * @param userId 微信用户管理ID
     * @return 微信用户管理
     */
    @Override
    public WxUser selectWxUserById(Long userId){
        return wxUserMapper.selectWxUserById(userId);
    }

    /**
     * 查询微信用户管理列表
     * 
     * @param wxUser 微信用户管理
     * @return 微信用户管理
     */
    @Override
    public List<WxUser> selectWxUserList(WxUser wxUser){
        return wxUserMapper.selectWxUserList(wxUser);
    }

    /**
     * 新增微信用户管理
     * 
     * @param wxUser 微信用户管理
     * @return 结果
     */
    @Override
    public int insertWxUser(WxUser wxUser){
        wxUser.setCreateTime(DateUtils.getNowDate());
        return wxUserMapper.insertWxUser(wxUser);
    }

    /**
     * 修改微信用户管理
     * 
     * @param wxUser 微信用户管理
     * @return 结果
     */
    @Override
    public int updateWxUser(WxUser wxUser){
        wxUser.setUpdateTime(DateUtils.getNowDate());
        return wxUserMapper.updateWxUser(wxUser);
    }

    /**
     * 删除微信用户管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWxUserByIds(String ids){
        return wxUserMapper.deleteWxUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信用户管理信息
     * 
     * @param userId 微信用户管理ID
     * @return 结果
     */
    @Override
    public int deleteWxUserById(Long userId){
        return wxUserMapper.deleteWxUserById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int getAllWxUser() {
        //获取微信列表
        try {
            List<WxMpUser> wxUserList=wxService.getAllWxMpUser();
            List<WxUser> iwxUserList=new ArrayList<>();
            for (WxMpUser wxMpUser : wxUserList) {
                iwxUserList.add(new WxUser(wxMpUser,"同步"));
            }
            wxUserMapper.clearWxUser();
            return wxUserMapper.insertWxUSers(iwxUserList);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
