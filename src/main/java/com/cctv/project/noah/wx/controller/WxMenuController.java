package com.cctv.project.noah.wx.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.wx.entity.WxLabel;
import com.cctv.project.noah.wx.entity.WxMenu;
import com.cctv.project.noah.wx.service.WxMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @program: noah->WxMenuController
 * @description: 微信菜单Controller
 * @author: yanhao
 * @create: 2019-12-02 09:16
 **/
@Controller
@RequestMapping("/wx/wxMenu")
public class WxMenuController extends BaseController {

    @Autowired
    WxMenuService wxMenuService;

    private String prefix="wx/menu";

    @GetMapping()
    public String wxMenu(){
        return prefix+"/menu";
    }

    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("wx:wxMenu:list")
    public List<WxMenu> list(WxMenu menu) {
        return wxMenuService.selectMenuList(menu);
    }


    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    @RequiresPermissions("wx:wxMenu:add")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        WxMenu menu = null;
        if (0L != parentId) {
            menu = wxMenuService.selectMenuById(parentId);
        } else {
            menu = new WxMenu();
            menu.setMenuId(0L);
            menu.setMenuName("一级菜单");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @ResponseBody
    @PostMapping("/add")
    @RequiresPermissions("wx:wxMenu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    public AjaxResult addSave(@Validated WxMenu menu) {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(wxMenuService.checkMenuNameUnique(menu))) {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wxMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    @RequiresPermissions("system:menu:edit")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", wxMenuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @ResponseBody
    @PostMapping("/edit")
    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    public AjaxResult editSave(@Validated WxMenu menu) {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(wxMenuService.checkMenuNameUnique(menu))) {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy("测试");
        return toAjax(wxMenuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @ResponseBody
    @GetMapping("/remove/{menuId}")
    @RequiresPermissions("wx:wxMenu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        if (wxMenuService.selectCountMenuByParentId(menuId) > 0) {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (wxMenuService.selectCountLableMenuByMenuId(menuId) > 0) {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        return toAjax(wxMenuService.deleteMenuById(menuId));
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", wxMenuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    /**
     * 加载所有菜单列表树
     */
    @ResponseBody
    @GetMapping("/menuTreeData")
    public List<Ztree> menuTreeData() {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = wxMenuService.menuTreeData(userId);
        return ztrees;
    }

    /**
     * 校验菜单名称
     */
    @ResponseBody
    @PostMapping("/checkMenuNameUnique")
    public String checkMenuNameUnique(WxMenu menu) {
        return wxMenuService.checkMenuNameUnique(menu);
    }



    /**
     * 加载角色菜单列表树
     */
    @ResponseBody
    @GetMapping("/labelMenuTreeData")
    public List<Ztree> labelMenuTreeData(WxLabel wxLabel) {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = wxMenuService.labelMenuTreeData(wxLabel);
        return ztrees;
    }

}
