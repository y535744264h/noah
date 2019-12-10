package com.cctv.project.noah.wx.mapper;

import com.cctv.project.noah.wx.entity.WxMenu;
import com.cctv.project.noah.wx.entity.WxMenuKey;

import java.util.List;

public interface WxMenuMapper {
    int deleteByPrimaryKey(WxMenuKey key);

    int insert(WxMenu record);

    int insertSelective(WxMenu record);

    WxMenu selectByPrimaryKey(WxMenuKey key);

    int updateByPrimaryKeySelective(WxMenu record);

    int updateByPrimaryKey(WxMenu record);

    List<WxMenu> selectMenuList(WxMenu menu);

    WxMenu selectMenuById(Long parentId);

    WxMenu checkMenuNameUnique(String menuName, Long parentId);

    int selectCountMenuByParentId(Long menuId);

    int selectCountLableMenuByMenuId(Long menuId);

    int deleteMenuById(Long menuId);

    List<String> selectMenuTree(Long labelId);
}