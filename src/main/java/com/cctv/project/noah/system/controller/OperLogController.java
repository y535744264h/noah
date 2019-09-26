package com.cctv.project.noah.system.controller;

import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.cctv.project.noah.system.core.domain.page.TableDataInfo;
import com.cctv.project.noah.system.entity.SysOperLog;
import com.cctv.project.noah.system.server.OperLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author by yanhao
 * @Classname OperLogController
 * @Description TODO
 * @Date 2019/9/18 11:35 上午
 */
@Controller
@RequestMapping("/monitor/operlog")
public class OperLogController extends BaseController{

    private String prefix = "system/operlog";

    @Autowired
    OperLogService operLogService;

    @GetMapping()
    public String operlog() {
        return prefix + "/operlog";
    }

//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(SysOperLog operLog, HttpServletRequest request){
//        startPage();
//        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
//        return getDataTable(list);
//    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysOperLog operLog, PageDomain pageDomain){
        Page<Map<String,Object>> list = operLogService.selectOperLogListMap(operLog,pageDomain);
        return getDataTable(list);
    }

}
