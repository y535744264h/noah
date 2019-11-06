package com.cctv.project.noah.demo.mapper;

import com.cctv.project.noah.demo.entity.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Long studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectStudent(Student student);

    Student checkStudentNameUnique(String studentName);

    int deleteUserIds(Long[] array);
}