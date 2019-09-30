package com.cctv.project.noah.system.server.impl;

import com.cctv.project.noah.system.core.domain.page.PageDomain;
import com.cctv.project.noah.system.core.domain.text.Convert;
import com.cctv.project.noah.system.entity.SysOperLog;
import com.cctv.project.noah.system.mapper.SysOperLogMapper;
import com.cctv.project.noah.system.server.OperLogService;
import com.cctv.project.noah.system.util.JdbcPaginationHelper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by yanhao
 * @Classname OperLogServerImpl
 * @Description TODO
 * @Date 2019/9/18 10:09 上午
 */
@Service
public class OperLogServerImpl implements OperLogService {

    @Autowired
    SysOperLogMapper sysOperLogMapper;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insOperLog(SysOperLog operLog) {
        return sysOperLogMapper.insertSelective(operLog);
    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return sysOperLogMapper.selectOperLogList(operLog);
    }

    @Override
    public Page<Map<String, Object>> selectOperLogListMap(SysOperLog operLog, PageDomain pageDomain) {
        String sql="select oper_id operId, title, business_type businessType, method, request_method requestMethod, operator_type operatorType, oper_name operName, dept_name deptName, oper_url operUrl, oper_ip operIp, oper_location operLocation, oper_param operParam, status, error_msg errorMsg, oper_time operTime " +
                "    from sys_oper_log";
        Map<String,Object> paramMap = new HashMap<String, Object>();
        return JdbcPaginationHelper.queryPage(jdbcTemplate,sql,paramMap,pageDomain);
    }

    @Override
    public int deleteOperLogByIds(String ids) {
        return sysOperLogMapper.deleteOperLogByIds(Convert.toStrArray(ids));
    }

    @Override
    public void cleanOperLog() {
        sysOperLogMapper.cleanOperLog();
    }

    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return sysOperLogMapper.selectByPrimaryKey(operId);
    }

}
