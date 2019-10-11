package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.entity.SysConfig;
import com.cctv.project.noah.system.mapper.SysConfigMapper;
import com.cctv.project.noah.system.service.ConfigService;
import com.cctv.project.noah.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by yanhao
 * @Classname ConfigServiceImpl
 * @Description TODO
 * @Date 2019/10/11 10:20 上午
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    SysConfigMapper selectConfig;

    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = selectConfig.selectConfig(config);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }
}
