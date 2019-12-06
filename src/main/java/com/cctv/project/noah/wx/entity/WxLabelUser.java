package com.cctv.project.noah.wx.entity;

import java.io.Serializable;

/**
 * wx_label_user
 * @author 
 */
public class WxLabelUser implements Serializable {
    /**
     * 标签ID
     */
    private Long labelId;

    /**
     * 用户ID
     */
    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}