package com.cctv.project.noah.system.server;

import com.cctv.project.noah.system.entity.DictData;

import java.util.List;

/**
 * @author by yanhao
 * @Classname SysDictDataService
 * @Description TODO
 * @Date 2019/9/29 2:32 下午
 */

public interface DictDataService {

    List<DictData> selectDictDataByType(String dictType);

    String selectDictLabel(String dictType, String dictValue);
}
