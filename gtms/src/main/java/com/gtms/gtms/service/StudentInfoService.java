package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.StudentInfo;
import com.gtms.gtms.vo.ModifyInfoVo;

public interface StudentInfoService extends IService<StudentInfo> {
    StudentInfo getInfo(Integer userId);
    int modifyInfo(ModifyInfoVo modifyInfoVo);
}
