package com.cctv.project.noah.wx.mapper;

import com.cctv.project.noah.wx.entity.WxLabelUser;

public interface WxLabelUserMapper {
    int deleteByPrimaryKey(Long labelId);

    int insert(WxLabelUser record);

    int insertSelective(WxLabelUser record);

    WxLabelUser selectByPrimaryKey(Long labelId);

    int updateByPrimaryKeySelective(WxLabelUser record);

    int updateByPrimaryKey(WxLabelUser record);

    int countUserLabelByLabelId(Long labelId);
}