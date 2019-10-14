package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.ShiroUtils;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by yanhao
 * @Classname MenuController
 * @Description TODO
 * @Date 2019/9/12 11:09 上午
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    private String prefix = "system/menu";

    @Autowired
    MenuService menuService;


    @GetMapping()
    public String menu() {
        return prefix + "/index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<SysMenu> list(SysMenu menu) {
        Long userId = UserIdUtil.user_id;
        return menuService.selectMenuList(menu, userId);
    }

    /**
     * 新增
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        SysMenu menu = null;
        if (0L != parentId) {
            menu = menuService.selectMenuById(parentId);
        } else {
            menu = new SysMenu();
            menu.setMenuId(0L);
            menu.setMenuName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysMenu menu) {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy("测试");
        return toAjax(menuService.insertMenu(menu));
    }


    /**
     * 修改菜单
     */
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysMenu menu) {
        if (UserConstants.MENU_NAME_NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy("测试");
        return toAjax(menuService.updateMenu(menu));
    }


    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.selectCountMenuByParentId(menuId) > 0) {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0) {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }


    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(SysMenu menu) {
        return menuService.checkMenuNameUnique(menu);
    }


    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon() {
        return prefix + "/icon";
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap) {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Ztree> menuTreeData() {
        Long userId = UserIdUtil.user_id;
        List<Ztree> ztrees = menuService.menuTreeData(userId);
        return ztrees;
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(SysRole role) {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = menuService.roleMenuTreeData(role, userId);
        return ztrees;
    }

}
