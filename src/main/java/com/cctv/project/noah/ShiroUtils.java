package com.cctv.project.noah;

import com.cctv.project.noah.system.entity.SysUser;

/**
 * @author by yanhao
 * @Classname ShiroUtils
 * @Description TODO
 * @Date 2019/9/18 10:16 上午
 */
public class ShiroUtils {
    public static String getIp() {
        return "111.206.175.44";
    }

    public static SysUser getSysUser() {
        return new SysUser();
    }

    public static String getLoginName() {
        return "闫浩";
    }

    public static String randomSalt() {
        return "123";
    }

    public static long getUserId() {
        return 1L;
    }

    public static void setSysUser(SysUser selectUserById) {
    }
}
