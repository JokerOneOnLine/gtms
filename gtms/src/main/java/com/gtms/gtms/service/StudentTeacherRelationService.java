package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.StudentTeacherRelation;

import java.util.List;
import java.util.Map;

public interface StudentTeacherRelationService extends IService<StudentTeacherRelation> {
    List<StudentTeacherRelation> getInfoByStudentNo(String studentNo);

    void deleteByThesisId(Integer[] ids);

    Map<String, Object> getInfoByTeacherNo(int page, int rows, String teacherNo);

    StudentTeacherRelation getInfoByThesisNo(String ThesisNo);

    void updateByThesisNo(String thesisNo, String opinion, String teacherOpinion);

    List<StudentTeacherRelation> getInfoByThesisNos(Integer[] ids);

    Map<String, Object> getAgreeThesisByTeacherNo(int page, int rows, String teacherNo);

    Map<String, Object> getAgreeThesisByStudentNo(int page, int rows, String studentNo);

    void updateTaskUrlByThesisNo(String taskUrl, String thesisNo);

    void updateThesisUrlByThesisNo(String taskUrl, String thesisNo);

    Map<String, Object> getThesisInfoDetail(StudentTeacherRelation studentTeacherRelation, int page, int rows);

    int selectPassNum();

    int selectUnderPassNumAndPassNum();

    int selectNoPassNum();

}
