package com.cctv.project.noah.wx.service.impl;
import com.cctv.project.noah.system.constant.UserConstants;
import com.cctv.project.noah.system.core.domain.Ztree;
import com.cctv.project.noah.system.entity.SysMenu;
import com.cctv.project.noah.system.util.StringUtils;
import com.cctv.project.noah.wx.entity.WxLabel;
import com.cctv.project.noah.wx.entity.WxMenu;
import com.cctv.project.noah.wx.mapper.WxMenuMapper;
import com.cctv.project.noah.wx.service.WxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: noah->WxMenuServiceImpl
 * @description:
 * @author: yanhao
 * @create: 2019-12-02 15:07
 **/
@Service
public class WxMenuServiceImpl implements WxMenuService {

    @Autowired
    WxMenuMapper wxMenuMapper;

    @Override
    public List<WxMenu> selectMenuList(WxMenu menu) {
        List<WxMenu> menuList = null;
        menuList = wxMenuMapper.selectMenuList(menu);
        return menuList;
    }

    @Override
    public WxMenu selectMenuById(Long parentId) {
        return wxMenuMapper.selectMenuById(parentId);
    }

    @Override
    public List<Ztree> menuTreeData(Long userId) {
        List<WxMenu> menuList = selectMenuList(null);
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }



    @Override
    public String checkMenuNameUnique(WxMenu menu) {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        WxMenu info = wxMenuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    @Override
    public int insertMenu(WxMenu menu) {
        return wxMenuMapper.insertSelective(menu);
    }

    @Override
    public int selectCountMenuByParentId(Long menuId) {
        return wxMenuMapper.selectCountMenuByParentId(menuId);
    }

    @Override
    public int selectCountLableMenuByMenuId(Long menuId) {
        return wxMenuMapper.selectCountLableMenuByMenuId(menuId);
    }

    @Override
    public int deleteMenuById(Long menuId) {
        return wxMenuMapper.deleteMenuById(menuId);
    }

    @Override
    public int updateMenu(WxMenu menu) {
        return wxMenuMapper.updateByPrimaryKeySelective(menu);
    }


    @Override
    public List<Ztree> labelMenuTreeData(WxLabel wxLabel) {
        Long labelId = wxLabel.getLabelId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<WxMenu> menuList = selectMenuList(null);
        if (StringUtils.isNotNull(labelId)) {
            List<String> roleMenuList = wxMenuMapper.selectMenuTree(labelId);
            ztrees = initZtree(menuList, roleMenuList, true);
        } else {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }


    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<WxMenu> menuList) {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList     菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag    是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<WxMenu> menuList, List<String> roleMenuList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (WxMenu menu : menuList) {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getMenuName());
            if (isCheck) {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(WxMenu menu, boolean permsFlag) {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}
