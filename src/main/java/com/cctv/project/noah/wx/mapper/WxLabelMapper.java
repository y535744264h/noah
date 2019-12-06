package com.cctv.project.noah.wx.mapper;

import com.cctv.project.noah.wx.entity.WxLabel;

import java.util.List;

public interface WxLabelMapper {
    int deleteByPrimaryKey(Long labelId);

    int insert(WxLabel record);

    int insertSelective(WxLabel record);

    WxLabel selectByPrimaryKey(Long labelId);

    int updateByPrimaryKeySelective(WxLabel record);

    int updateByPrimaryKey(WxLabel record);

    List<WxLabel> selectaLabelList(WxLabel wxLabel);

    WxLabel checkLabelNameUnique(String labelName);

    WxLabel checkLabelKeyUnique(String labelKey);

    int insertLabel(WxLabel label);

    int deleteLabelByIds(Long[] labelIds);

    int insertLabelList(List<WxLabel> insLabelList);

    int deleteLabelMenuByLabelId(Long labelId);
}