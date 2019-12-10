package com.cctv.project.noah.wx.mapper;

import com.cctv.project.noah.wx.entity.WxMenuLabelKey;

import java.util.List;

public interface WxMenuLabelMapper {
    int deleteByPrimaryKey(WxMenuLabelKey key);

    int insert(WxMenuLabelKey record);

    int insertSelective(WxMenuLabelKey record);

    int batchLabelMenu(List<WxMenuLabelKey> list);

}