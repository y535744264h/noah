package com.cctv.project.noah.wx.service;

import com.cctv.project.noah.wx.entity.WxLabel;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * @program: noah->WxLabelService
 * @description:
 * @author: yanhao
 * @create: 2019-12-05 09:46
 **/
public interface WxLabelService {

    List<WxLabel> selectaLabelList(WxLabel wxLabel);

    String checkLabelNameUnique(WxLabel wxLabel);

    String checkLabelKeyUnique(WxLabel wxLabel);


    int insertLabel(WxLabel label);

    int deleteLabelByIds(String ids);

    Integer reloadWxData() throws WxErrorException;

    WxLabel selectLabelById(Long labelId);

    int updateLabel(WxLabel wxLabel) throws WxErrorException;
}
