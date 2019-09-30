package com.cctv.project.noah.system.mapper;

import com.cctv.project.noah.system.entity.DictData;

import java.util.List;

public interface DictDataMapper {
    int deleteByPrimaryKey(Long dictCode);

    int insert(DictData record);

    int insertSelective(DictData record);

    DictData selectByPrimaryKey(Long dictCode);

    int updateByPrimaryKeySelective(DictData record);

    int updateByPrimaryKey(DictData record);


    List<DictData> selectDictDataByType(String dictType);

    String selectDictLabel(String dictType, String dictValue);
}