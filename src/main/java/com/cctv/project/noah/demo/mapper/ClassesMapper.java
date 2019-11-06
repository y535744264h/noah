package com.cctv.project.noah.demo.mapper;

import com.cctv.project.noah.demo.entity.Classes;

import java.util.List;

public interface ClassesMapper {
    int deleteByPrimaryKey(Long classId);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Long classId);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Class> selectAll();
}