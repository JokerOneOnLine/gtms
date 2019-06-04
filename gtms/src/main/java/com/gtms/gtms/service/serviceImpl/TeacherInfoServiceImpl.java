package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.mapper.TeacherInfoMapper;
import com.gtms.gtms.service.TeacherInfoService;
import com.gtms.gtms.vo.ModifyInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 84644
 * @Date: 2019/4/8 12:05
 * @Description:
 **/
@Service
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements TeacherInfoService {
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public TeacherInfo getInfo(Integer userId) {
        return teacherInfoMapper.getInfo(userId);
    }

    @Override
    public int modifyInfo(ModifyInfoVo modifyInfoVo) {
        return teacherInfoMapper.modifyInfo(modifyInfoVo);
    }
}
