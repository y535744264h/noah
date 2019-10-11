package com.cctv.project.noah.system.service;

/**
 * @author by yanhao
 * @Classname PasswordService
 * @Description TODO
 * @Date 2019/10/8 4:30 下午
 */
public interface PasswordService {
    String encryptPassword(String loginName, String password, String salt);
}
