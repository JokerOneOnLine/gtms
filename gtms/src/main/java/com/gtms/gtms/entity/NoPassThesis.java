package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/14 0:29
 * @Description:
 **/
@TableName("T_NO_PASS_THESIS")
@Data
public class NoPassThesis implements Serializable {
    private static final long serialVersionUID = 9209148812366007499L;

    @TableId("id")
    private Integer id;

    @TableField("student_no")
    private String studentNo;

    @TableField("student_name")
    private String studentName;

    @TableField("student_class")
    private String studentClass;

    @TableField("teacher_no")
    private String teacherNo;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("thesis_No")
    private String thesisNo;

    @TableField("thesis_title")
    private String thesisTitle;

    @TableField("teacher_opinion")
    private String teacherOpinion;

}
