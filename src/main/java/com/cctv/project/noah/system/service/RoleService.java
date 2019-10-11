package com.cctv.project.noah.system.service;

import com.cctv.project.noah.system.entity.SysRole;

import java.util.List;

/**
 * @author by yanhao
 * @Classname RoleService
 * @Description TODO
 * @Date 2019/10/8 4:29 下午
 */
public interface RoleService {

    List<SysRole> selectRoleAll();

    public List<SysRole> selectRolesByUserId(Long userId);
}
