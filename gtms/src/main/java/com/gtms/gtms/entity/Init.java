package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/10 8:21
 * @Description:
 **/
@TableName("T_INIT")
@Data
public class Init implements Serializable {
    private static final long serialVersionUID = 5149480756168908836L;

    @TableId("id")
    private Integer id;

    @TableField("student_num")
    private Integer studentNum;

    @TableField("teacher_num")
    private Integer teacherNum;

}
