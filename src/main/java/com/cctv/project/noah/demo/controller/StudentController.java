package com.cctv.project.noah.demo.controller;

import com.cctv.project.noah.demo.entity.Student;
import com.cctv.project.noah.demo.service.StudentService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.enmus.BusinessType;
import com.cctv.project.noah.system.util.poi.ExcelUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by yanhao
 * @Classname StudentController
 * @Description 学生Controller
 * @Date 2019/10/18 2:40 下午
 */
@Controller
@RequestMapping("/demo/student")
public class StudentController extends BaseController {

    private String prefix="demo/student";

    @Autowired
    StudentService studentService;

    @GetMapping()
    public String student() {
        return prefix + "/student";
    }

    @ResponseBody
    @RequestMapping("list")
    public TableDataInfo list(Student student){
        startPage();
        List<Student> studentList=studentService.selectStudent(student);
        return getDataTable(studentList);
    }

    @GetMapping("add")
    public String add(){
        return prefix+"/add";
    }


    @PostMapping("add")
    @ResponseBody
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    public AjaxResult addStudent(Student student){
        if(studentService.checkStudentNameUnique(student).equals("0")){
            int role=studentService.addStudent(student);
            System.out.println(role);
            return toAjax(role);
        }else{
            return error("添加用户失败，学生姓名已存在");
        }

    }

    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long studentId, ModelMap mmap) {
        mmap.put("student", studentService.selectStudentByStudentId(studentId));
        return prefix + "/edit";
    }

    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editStudent(@Validated Student student){
        try {
            return toAjax(studentService.editStudent(student));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(studentService.deleteUserByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }


    @PostMapping("/checkStudentNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(Student student) {
        return studentService.checkStudentNameUnique(student);
    }

    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Student student) {
        List<Student> list = studentService.selectStudent(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        return util.exportExcel(list, "学生数据");
    }

}
