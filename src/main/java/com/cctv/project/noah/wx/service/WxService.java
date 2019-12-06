package com.cctv.project.noah.wx.service;

import com.cctv.project.noah.wx.entity.WxLabel;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: noah->WxService
 * @description:
 * @author: yanhao
 * @create: 2019-12-05 16:19
 **/
@Component
public class WxService {

    @Autowired
    WxMpService wxMpService;

    /**
     * 新增 标签
     * @param wxLabel 标签对象
     * @return 标签ID
     * @throws WxErrorException
     */
    public Long insLabel(WxLabel wxLabel) throws WxErrorException {
        WxUserTag wxUserTag=wxMpService.getUserTagService().tagCreate(wxLabel.getLabelName());
        return wxUserTag.getId();
    }

    public Boolean deleteLabel(Long labelId) throws WxErrorException {
        return wxMpService.getUserTagService().tagDelete(labelId);
    }

    /**
     * 修改标签
     * @param wxLabel 标签对象
     * @return 是否成功
     * @throws WxErrorException
     */
    public Boolean updateLabel(WxLabel wxLabel) throws WxErrorException {
        Boolean res = wxMpService.getUserTagService().tagUpdate(wxLabel.getLabelId(), wxLabel.getLabelName());
        return res;
    }

    /**
     * 获取标签列表
     * @return
     * @throws WxErrorException
     */
    public List<WxLabel> getLabelList() throws WxErrorException {
        List<WxLabel> wxLabelList=new ArrayList<>();
        List<WxUserTag> res = wxMpService.getUserTagService().tagGet();
        for (WxUserTag re : res) {
            WxLabel wxLabel=new WxLabel();
            wxLabel.setLabelId(re.getId());
            wxLabel.setLabelKey(re.getName());
            wxLabel.setLabelName(re.getName());
            wxLabel.setLabelCount(re.getCount());
            wxLabel.setStatus("0");
            wxLabel.setCreateBy("微信同步");
            wxLabel.setRemark("微信同步数据");
            wxLabelList.add(wxLabel);
        }
        return wxLabelList;
    }

}
