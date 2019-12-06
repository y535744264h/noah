package com.cctv.project.noah.wx.entity;

import java.io.Serializable;

/**
 * wx_menu_label
 * @author 
 */
public class WxMenuLabelKey implements Serializable {
    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 标签ID
     */
    private Long labelId;

    private static final long serialVersionUID = 1L;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }
}