package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/8 10:48
 * @Description:
 **/
@TableName("T_STUDENT_INFO")
@Data
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 2165728557695876124L;

    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("student_no")
    private String studentNo = StringUtils.EMPTY;

    @TableField("student_name")
    private String studentName = StringUtils.EMPTY;

    @TableField("student_major")
    private String studentMajor = StringUtils.EMPTY;

    @TableField("student_instructor")
    private String studentInstructor = StringUtils.EMPTY;

    @TableField("student_class")
    private String studentClass = StringUtils.EMPTY;

    @TableField("student_phone")
    private String studentPhone = StringUtils.EMPTY;

    @TableField("student_email")
    private String studentEmail = StringUtils.EMPTY;
}
