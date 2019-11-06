package com.cctv.project.noah.demo.service;

import com.cctv.project.noah.demo.entity.Student;

import java.util.List;

/**
 * @author by yanhao
 * @Classname StudentService
 * @Description TODO
 * @Date 2019/10/21 9:50 上午
 */
public interface StudentService {

    /**
     * 获取所有班级
     * @return
     */
    public List<Class> getAllClass();

    public List<Student> selectStudent(Student student);

    /**
     * 检查学生姓名是否存在
     * @param studentName
     * @return
     */
    String checkStudentNameUnique(Student student);

    int addStudent(Student student);

    Student selectStudentByStudentId(Long studentId);

    int editStudent(Student student);

    int deleteUserByIds(String ids);
}
