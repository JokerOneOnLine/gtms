package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gtms.gtms.entity.NoPassThesis;
import com.gtms.gtms.entity.StudentTeacherRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NopassThesisMapper extends BaseMapper<NoPassThesis> {
    List<StudentTeacherRelation> selectAll(@Param("md") StudentTeacherRelation studentTeacherRelation);
}
