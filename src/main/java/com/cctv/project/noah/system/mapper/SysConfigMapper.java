package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.SysConfig;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    SysConfig selectConfig(SysConfig config);
}