package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gtms.gtms.entity.StudentTeacherRelation;
import com.gtms.gtms.vo.ThesisInfoDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentTeacherRelationMapper extends BaseMapper<StudentTeacherRelation> {
    List<StudentTeacherRelation> getInfoByStudentNo(@Param("studentNo") String studentNo);

    void deleteByThesisId(@Param("ids") String sb);

    List<StudentTeacherRelation> getInfoByTeacherNo(@Param("teacherNo") String teacherNo, Pagination page);

    void updateByThesisNo(@Param("thesisNo") String thesisNo, @Param("opinion") String opinion, @Param("teacherOpinion") String teacherOpinion);

    StudentTeacherRelation getInfoByThesisNo(@Param("thesisNo") String thesisNo);

    List<StudentTeacherRelation> getInfoByThesisNos(@Param("thesisNos") Integer[] ids);

    List<StudentTeacherRelation> getAgreeThesisByTeacherNo(@Param("teacherNo") String teacherNo, Pagination page);

    List<StudentTeacherRelation> getAgreeThesisByStudentNo(@Param("studentNo") String studentNo, Pagination page);

    void updateTaskUrlByThesisNo(@Param("taskUrl") String taskUrl, @Param("thesisNo") String thesisNo);

    void updateThesisUrlByThesisNo(@Param("thesisUrl") String thesisUrl, @Param("thesisNo") String thesisNo);

    List<StudentTeacherRelation> getThesisInfoDetail(@Param("md") StudentTeacherRelation studentTeacherRelation);

    int selectPassNum();

    int selectUnderPassNumAndPassNum();

    int selectNoPassNum();
}
