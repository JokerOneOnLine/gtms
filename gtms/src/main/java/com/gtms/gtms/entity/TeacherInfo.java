package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/8 10:54
 * @Description:
 **/
@TableName("T_TEACHER_INFO")
@Data
public class TeacherInfo implements Serializable {
    private static final long serialVersionUID = 549523433102030309L;

    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("teacher_no")
    private String teacherNo = StringUtils.EMPTY;

    @TableField("teacher_name")
    private String teacherName = StringUtils.EMPTY;

    @TableField("teacher_title")
    private String teacherTitle = StringUtils.EMPTY;

    @TableField("teacher_education")
    private String teacherEducation = StringUtils.EMPTY;

    @TableField("teacher_phone")
    private String teacherPhone = StringUtils.EMPTY;

    @TableField("teacher_email")
    private String teacherEmail = StringUtils.EMPTY;
}
