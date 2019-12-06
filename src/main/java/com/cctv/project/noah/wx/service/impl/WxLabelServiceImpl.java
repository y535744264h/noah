package com.cctv.project.noah.wx.service.impl;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.exception.BusinessException;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.wx.entity.WxLabel;
import com.cctv.project.noah.wx.entity.WxMenu;
import com.cctv.project.noah.wx.entity.WxMenuLabelKey;
import com.cctv.project.noah.wx.mapper.WxLabelMapper;
import com.cctv.project.noah.wx.mapper.WxLabelUserMapper;
import com.cctv.project.noah.wx.mapper.WxMenuLabelMapper;
import com.cctv.project.noah.wx.service.WxLabelService;
import com.cctv.project.noah.wx.service.WxService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: noah->WxLabelServiceImpl
 * @description:
 * @author: yanhao
 * @create: 2019-12-05 09:46
 **/
@Service
public class WxLabelServiceImpl implements WxLabelService {

    @Autowired
    WxLabelMapper wxLabelMapper;

    @Autowired
    WxMenuLabelMapper wxMenuLabelMapper;

    @Autowired
    WxLabelUserMapper wxLabelUserMapper;

    @Autowired
    WxService wxService;

    @Override
    public List<WxLabel> selectaLabelList(WxLabel wxLabel) {
        return wxLabelMapper.selectaLabelList(wxLabel);
    }

    @Override
    public String checkLabelNameUnique(WxLabel wxLabel) {
        Long labelId = StringUtils.isNull(wxLabel.getLabelId()) ? -1L : wxLabel.getLabelId();
        WxLabel info = wxLabelMapper.checkLabelNameUnique(wxLabel.getLabelName());
        boolean bool=false;
        try {
            List<WxLabel> wxLabelList=wxService.getLabelList();
            for (WxLabel label : wxLabelList) {
                if(label.getLabelName().equals(wxLabel.getLabelName())){
                    bool=true;
                }
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        if ((StringUtils.isNotNull(info) && info.getLabelId().longValue() != labelId.longValue())|| (bool&&info.getLabelId().longValue() != labelId.longValue())) {
            return UserConstants.LABEL_NAME_NOT_UNIQUE;
        }
        return UserConstants.LABEL_NAME_UNIQUE;
    }

    @Override
    public String checkLabelKeyUnique(WxLabel wxLabel) {
        Long labelId = StringUtils.isNull(wxLabel.getLabelId()) ? -1L : wxLabel.getLabelId();
        WxLabel info = wxLabelMapper.checkLabelKeyUnique(wxLabel.getLabelKey());
        if (StringUtils.isNotNull(info) && info.getLabelId().longValue() != labelId.longValue()) {
            return UserConstants.LABEL_KEY_NOT_UNIQUE;
        }
        return UserConstants.LABEL_KEY_UNIQUE;
    }




    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertLabel(WxLabel label) {
        int count=1;
        Long id=null;
        try {
            id=wxService.insLabel(label);
            label.setLabelId(id);
            // 新增角色信息
            count*=wxLabelMapper.insertLabel(label);
            count*=insertLabelMenu(label);
        } catch (WxErrorException e) {
            try {
                wxService.deleteLabel(id);
            } catch (WxErrorException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            count=0;
        }
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLabelByIds(String ids) {
        Long[] labelIds = Convert.toLongArray(ids);
        for (Long labelId : labelIds) {
            WxLabel wxLabel = selectLabelById(labelId);
            if (countUserLabelByLabelId(labelId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", wxLabel.getLabelName()));
            }
        }
        int count=wxLabelMapper.deleteLabelByIds(labelIds);
        for (Long labelId : labelIds) {
            try {
                wxService.deleteLabel(labelId);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public Integer reloadWxData() throws WxErrorException {

        //本地修改
        List<WxLabel> updateLabelList=new ArrayList<>();


        //查出本地所有标签 与微信服务器对比，多处的新增 与微信服务器不同的 以微信服务器为准 ID 为条件
        List<WxLabel> localLabelList=wxLabelMapper.selectaLabelList(null);
        //调用微信接口
        List<WxLabel> wxLabelList=wxService.getLabelList();

        //本地新增
        List<WxLabel> insLabelList=new ArrayList<>();
        //先循环一遍 如果本地没有 服务器有的 则先同步到本地
        for (WxLabel label : wxLabelList) {
            boolean bool=false;
            for (WxLabel wxLabel : localLabelList) {
                if(label.getLabelId().longValue()==wxLabel.getLabelId().longValue()){
                    bool=true;
                    continue;
                }
            }
            //新增本地
            if(!bool){
                insLabelList.add(label);
            }
        }
        //新增对象
        if(insLabelList.size()>0){
            wxLabelMapper.insertLabelList(insLabelList);
        }

        //如果 微信没有 本地有的 则新增
        for (WxLabel wxLabel : localLabelList) {
            boolean bool=false;
            for (WxLabel label : wxLabelList) {
                if(label.getLabelId().longValue()==wxLabel.getLabelId().longValue()){
                    bool=true;
                    //如果ID一一样，判断下名称是否一致
                    if(!label.getLabelName().equals(wxLabel.getLabelName())){
                        //不一致就修改微信
                        wxService.updateLabel(wxLabel);
                    }
                    //如果一致就同步COUNT
                    wxLabel.setLabelCount(label.getLabelCount());
                    wxLabelMapper.updateByPrimaryKey(wxLabel);
                }
            }
            if(!bool){
                wxService.insLabel(wxLabel);
            }
        }
        return 1;
    }


    private int countUserLabelByLabelId(Long labelId) {
        return wxLabelUserMapper.countUserLabelByLabelId(labelId);
    }

    @Override
    public WxLabel selectLabelById(Long label) {
        return wxLabelMapper.selectByPrimaryKey(label);
    }

    @Override
    public int updateLabel(WxLabel wxLabel) throws WxErrorException {
        wxLabel.setUpdateTime(new Date());
        wxLabel.setUpdateBy(ShiroUtils.getLoginName());
        wxService.updateLabel(wxLabel);
        // 修改标签信息
        wxLabelMapper.updateByPrimaryKeySelective(wxLabel);
        // 删除角色与菜单关联
        wxLabelMapper.deleteLabelMenuByLabelId(wxLabel.getLabelId());
        return insertLabelMenu(wxLabel);
    }

    private int insertLabelMenu(WxLabel label) {
        int rows = 1;
        // 新增用户与角色管理
        List<WxMenuLabelKey> list = new ArrayList<WxMenuLabelKey>();
        for (Long menuId : label.getMenuIds()) {
            WxMenuLabelKey ml = new WxMenuLabelKey();
            ml.setLabelId(label.getLabelId());
            ml.setMenuId(menuId);
            list.add(ml);
        }
        if (list.size() > 0) {
            rows = wxMenuLabelMapper.batchLabelMenu(list);
        }
        return rows;
    }


}
