package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Generated;
import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/10 8:58
 * @Description:
 **/
@TableName("T_STUDENT_TEACHER_RELATION")
@Data
public class StudentTeacherRelation implements Serializable {
    private static final long serialVersionUID = -1001137799180443566L;

    @TableId("id")
    private Integer id;

    @TableField("student_no")
    private String studentNo = StringUtils.EMPTY;

    @TableField("student_name")
    private String studentName = StringUtils.EMPTY;

    @TableField("student_class")
    private String studentClass = StringUtils.EMPTY;

    @TableField(exist = false)
    private String studentMajor = StringUtils.EMPTY;

    @TableField(exist = false)
    private String studentInstructor = StringUtils.EMPTY;

    @TableField(exist = false)
    private String studentEmail = StringUtils.EMPTY;

    @TableField(exist = false)
    private String studentPhone = StringUtils.EMPTY;

    @TableField("teacher_no")
    private String teacherNo = StringUtils.EMPTY;

    @TableField("teacher_name")
    private String teacherName = StringUtils.EMPTY;

    @TableField("teacher_title")
    private String teacherTitle = StringUtils.EMPTY;

    @TableField("teacher_email")
    private String teacherEmail = StringUtils.EMPTY;

    @TableField(exist = false)
    private String teacherPhone = StringUtils.EMPTY;

    @TableField("thesis_No")
    private String thesisNo = StringUtils.EMPTY;

    @TableField("thesis_title")
    private String thesisTitle = StringUtils.EMPTY;

    @TableField("teacher_opinion")
    private String teacherOpinion = StringUtils.EMPTY;

    @TableField("opinion_flag")
    private Integer opinionFlag;

    @TableField("task_status")
    private Integer taskStatus;

    @TableField("task_url")
    private String taskUrl = StringUtils.EMPTY;

    @TableField("thesis_status")
    private String thesisStatus = StringUtils.EMPTY;

    @TableField("thesis_url")
    private String thesisUrl = StringUtils.EMPTY;

    @TableField(exist = false)
    private String opinionFlagStr = StringUtils.EMPTY;
}
