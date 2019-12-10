package com.cctv.project.noah.wx.controller;

import java.util.List;

import com.cctv.project.noah.wx.entity.WxUser;
import com.cctv.project.noah.wx.service.WxUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;

/**
 * 微信用户管理Controller
 * 
 * @author yanhao
 * @date 2019-12-09
 */
@Controller
@RequestMapping("/wx/user")
public class WxUserController extends BaseController{
    private String prefix = "wx/user";

    @Autowired
    private WxUserService wxUserService;

    @RequiresPermissions("wx:user:view")
    @GetMapping()
    public String user(){
        return prefix + "/user";
    }

    /**
     * 查询微信用户管理列表
     */
    @RequiresPermissions("wx:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WxUser wxUser){
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户管理列表
     */
    @RequiresPermissions("wx:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WxUser wxUser){
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增微信用户管理
     */
    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    /**
     * 新增保存微信用户管理
     */
    @RequiresPermissions("wx:user:add")
    @Log(title = "微信用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxUser wxUser){
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改微信用户管理
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap){
        WxUser wxUser = wxUserService.selectWxUserById(userId);
        mmap.put("wxUser", wxUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信用户管理
     */
    @RequiresPermissions("wx:user:edit")
    @Log(title = "微信用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WxUser wxUser){
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除微信用户管理
     */
    @RequiresPermissions("wx:user:remove")
    @Log(title = "微信用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        return toAjax(wxUserService.deleteWxUserByIds(ids));
    }


    @ResponseBody
    @PostMapping("/getAllUser")
    @RequiresPermissions("wx:user:getuser")
    @Log(title = "微信用户管理", businessType = BusinessType.SYNCWXUSER)
    public AjaxResult getAllUser(){
        //删除本地所有数据 同步微信数据
        try {
            AjaxResult.success(wxUserService.getAllWxUser());
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success();
    }
}
