package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.vo.ModifyInfoVo;

public interface TeacherInfoService extends IService<TeacherInfo> {
    TeacherInfo getInfo(Integer userId);
    int modifyInfo(ModifyInfoVo modifyInfoVo);
}
