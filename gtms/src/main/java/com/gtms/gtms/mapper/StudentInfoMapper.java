package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gtms.gtms.entity.StudentInfo;
import com.gtms.gtms.vo.ModifyInfoVo;
import org.apache.ibatis.annotations.Param;

public interface StudentInfoMapper extends BaseMapper<StudentInfo> {
    StudentInfo getInfo(Integer userId);
    int modifyInfo(@Param("md") ModifyInfoVo modifyInfoVo);
}
