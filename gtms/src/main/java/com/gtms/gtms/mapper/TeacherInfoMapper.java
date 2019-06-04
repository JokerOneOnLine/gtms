package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.vo.ModifyInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 84644
 * @Date: 2019/4/8 11:54
 * @Description:
 **/
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {
    TeacherInfo getInfo(Integer userId);
    int modifyInfo(@Param("md") ModifyInfoVo modifyInfoVo);
}

