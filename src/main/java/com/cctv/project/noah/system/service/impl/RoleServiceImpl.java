package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.entity.SysRole;
import com.cctv.project.noah.system.mapper.SysRoleMapper;
import com.cctv.project.noah.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yanhao
 * @Classname RoleServiceImpl
 * @Description TODO
 * @Date 2019/10/9 9:05 上午
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectRoleAll() {
        return sysRoleMapper.selectRoleList(new SysRole());
    }

    @Override
    public List<SysRole> selectRolesByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = selectRoleAll();
        for (SysRole role : roles) {
            for (SysRole userRole : userRoles) {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }
}
