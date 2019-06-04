package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/9 12:00
 * @Description:
 **/
@Data
@TableName("T_THESIS_INFO")
public class ThesisInfo implements Serializable {
    private static final long serialVersionUID = 1729202011328459139L;

    @TableId("id")
    private Integer id;

    @TableField("thesis_title")
    private String thesisTitle;

    @TableField("teacher_no")
    private String teacherNo;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("notice_info")
    private String noticeInfo;

    @TableField("select_num")
    private Integer selectNum;

    @TableField(exist = false)
    private String teacherTitle;

    @TableField(exist = false)
    private String teacherEmail;

    @TableField(exist = false)
    private String teacherPhone;

}
