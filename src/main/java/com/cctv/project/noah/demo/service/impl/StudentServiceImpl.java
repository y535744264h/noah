package com.cctv.project.noah.demo.service.impl;

import com.cctv.project.noah.demo.entity.Student;
import com.cctv.project.noah.demo.mapper.ClassesMapper;
import com.cctv.project.noah.demo.mapper.StudentMapper;
import com.cctv.project.noah.demo.service.StudentService;
import com.cctv.project.noah.system.core.domain.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by yanhao
 * @Classname StudentServiceImpl
 * @Description TODO
 * @Date 2019/10/21 9:53 上午
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    ClassesMapper classesMapper;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Class> getAllClass() {
        return classesMapper.selectAll();
    }

    @Override
    public List<Student> selectStudent(Student student) {
        return studentMapper.selectStudent(student);
    }

    @Override
    public String checkStudentNameUnique(Student student) {
        Student stu=studentMapper.checkStudentNameUnique(student.getStudentName());
        if(stu!=null&&!stu.getStudentId().equals(student.getStudentId())){
            return "1";
        }else{
            return "0";
        }
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    public Student selectStudentByStudentId(Long studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public int editStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public int deleteUserByIds(String ids) {
        return studentMapper.deleteUserIds(Convert.toLongArray(ids));
    }

}
