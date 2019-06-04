package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gtms.gtms.entity.ThesisInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThesisInfoMapper extends BaseMapper<ThesisInfo> {
    List<ThesisInfo> getThesisInfo(Pagination page);

    List<ThesisInfo> getThesisInfoByTeacherNo(Pagination pageRecord,@Param("teacherNo") String teacherNo);
}
