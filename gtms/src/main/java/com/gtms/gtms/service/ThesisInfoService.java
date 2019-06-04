package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.ThesisInfo;

import java.util.Map;

public interface ThesisInfoService extends IService<ThesisInfo> {
    Map<String, Object> getThesisInfo(int page, int rows);

    Map<String, Object> getThesisInfoByTeacherNo(int page, int rows,String theacherNo);
}
