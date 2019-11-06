package com.cctv.project.noah.demo.controller;

import com.cctv.project.noah.demo.entity.Student;
import com.cctv.project.noah.demo.service.StudentService;
import com.cctv.project.noah.system.annotation.Log;
import com.cctv.project.noah.system.controller.BaseController;
import com.cctv.project.noah.system.core.domain.AjaxResult;
import com.cctv.project.noah.system.core.domain.page.PageDomain;
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
import java.util.Map;

/**
 * @author by yanhao
 * @Classname StudentController
 * @Description 学生Controller
 * @Date 2019/10/18 2:40 下午
 */
@Controller
@RequestMapping("/demo/studentDemo")
public class StudentDemoController extends BaseController {

    private String prefix="demo/student_demo";

    @Autowired
    StudentService studentService;

    @RequestMapping()
    public String student(PageDomain pageDomain,Student student, Map<String,Object> map) {
        if(pageDomain==null||pageDomain.getPageNum()==null){
            startPage(1,10,pageDomain.getIsAsc(),pageDomain.getOrderByColumn());
        }else{
            startPage();
        }
        List<Student> studentList=studentService.selectStudent(student);
        PageInfo pageInfo=new PageInfo(studentList);
        map.put("list",studentList);
        map.put("pageDomain",pageDomain);
        map.put("pageInfo",pageInfo);
        return prefix + "/student";
    }




}
