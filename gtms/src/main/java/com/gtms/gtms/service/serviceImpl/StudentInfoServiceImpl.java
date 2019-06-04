package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gtms.gtms.entity.StudentInfo;
import com.gtms.gtms.mapper.StudentInfoMapper;
import com.gtms.gtms.service.StudentInfoService;
import com.gtms.gtms.vo.ModifyInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 84644
 * @Date: 2019/4/8 11:54
 * @Description:
 **/
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements StudentInfoService {
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public StudentInfo getInfo(Integer userId) {
        return studentInfoMapper.getInfo(userId);
    }

    @Override
    public int modifyInfo(ModifyInfoVo modifyInfoVo) {
        return studentInfoMapper.modifyInfo(modifyInfoVo);
    }
}
