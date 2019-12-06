package com.cctv.project.noah.wx.service;

import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.wx.entity.WxLabel;
import com.cctv.project.noah.wx.entity.WxMenu;

import java.util.List;

/**
 * @author by yanhao
 * @Classname WxMenuService
 * @Description TODO
 * @Date 2019/12/2 3:06 下午
 */
public interface WxMenuService {

    List<WxMenu> selectMenuList(WxMenu menu);

    WxMenu selectMenuById(Long parentId);

    List<Ztree> menuTreeData(Long userId);

    String checkMenuNameUnique(WxMenu menu);

    int insertMenu(WxMenu menu);

    int selectCountMenuByParentId(Long menuId);

    int selectCountLableMenuByMenuId(Long menuId);

    int deleteMenuById(Long menuId);

    int updateMenu(WxMenu menu);

    List<Ztree> labelMenuTreeData(WxLabel wxLabel);
}
