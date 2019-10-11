package com.cctv.project.noah.system.service.impl;

import com.cctv.project.noah.system.service.PasswordService;
import org.springframework.stereotype.Service;

/**
 * @author by yanhao
 * @Classname PasswordServiceImpl
 * @Description TODO
 * @Date 2019/10/9 10:30 上午
 */
@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String encryptPassword(String loginName, String password, String salt) {
        return "";
    }
}
