package com.cctv.project.noah.wx.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.wx.entity.WxLabel;
import com.cctv.project.noah.wx.service.WxLabelService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: noah->LableController
 * @description:
 * @author: yanhao
 * @create: 2019-12-04 16:37
 **/

@Controller
@RequestMapping("/wx/label")
public class WxLabelController extends BaseController {

    private String prefix="wx/label";

    @Autowired
    WxLabelService wxLabelService;

    @GetMapping()
    public String label(){
        return prefix+"/label";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("wx:label:list")
    public TableDataInfo list(WxLabel wxLabel){
        startPage();
        List<WxLabel> list = wxLabelService.selectaLabelList(wxLabel);
        return getDataTable(list);
    }

    /**
     * 新增标签
     */
    @GetMapping("/add")
    @RequiresPermissions("wx:label:add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("wx:label:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    public AjaxResult addSave(@Validated WxLabel label) {
        if (UserConstants.LABEL_NAME_NOT_UNIQUE.equals(wxLabelService.checkLabelNameUnique(label))) {
            return error("新增标签'" + label.getLabelName() + "'失败，角色名称已存在");
        } else if (UserConstants.LABEL_KEY_NOT_UNIQUE.equals(wxLabelService.checkLabelKeyUnique(label))) {
            return error("新增标签'" + label.getLabelName() + "'失败，角色权限已存在");
        }
        label.setCreateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(wxLabelService.insertLabel(label));

    }

    @ResponseBody
    @PostMapping("/remove")
    @RequiresPermissions("wx:label:remove")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    public AjaxResult remove(String ids) {
        try {
            return toAjax(wxLabelService.deleteLabelByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }


    /**
     * 修改角色
     */
    @GetMapping("/edit/{labelId}")
    @RequiresPermissions("wx:label:edit")
    public String edit(@PathVariable("labelId") Long labelId, ModelMap mmap) {
        mmap.put("label", wxLabelService.selectLabelById(labelId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @ResponseBody
    @PostMapping("/edit")
    @RequiresPermissions("wx:label:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    public AjaxResult editSave(@Validated WxLabel wxLabel) {
        if (UserConstants.LABEL_NAME_NOT_UNIQUE.equals(wxLabelService.checkLabelNameUnique(wxLabel))) {
            return error("修改标签'" + wxLabel.getLabelName() + "'失败，角色名称已存在");
        } else if (UserConstants.LABEL_KEY_NOT_UNIQUE.equals(wxLabelService.checkLabelKeyUnique(wxLabel))) {
            return error("修改角色'" + wxLabel.getLabelName() + "'失败，角色权限已存在");
        }
        wxLabel.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        try {
            return toAjax(wxLabelService.updateLabel(wxLabel));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return toAjax(0);
    }


    /**
     * 校验名称标签
     */
    @PostMapping("/checkLabelNameUnique")
    @ResponseBody
    public String checkLabelNameUnique(WxLabel wxLabel) {
        return wxLabelService.checkLabelNameUnique(wxLabel);
    }

    /**
     * 校验角色权限
     */
    @PostMapping("/checkLabelKeyUnique")
    @ResponseBody
    public String checkRoleKeyUnique(WxLabel wxLabel) {
        return wxLabelService.checkLabelKeyUnique(wxLabel);
    }


    /**
     * 同步微信标签
     */
    @ResponseBody
    @RequestMapping("/reloadWxData")
    public AjaxResult reloadWxData() throws WxErrorException {

        Integer count=wxLabelService.reloadWxData();
        return AjaxResult.success(count);
    }

}
