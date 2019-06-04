package com.gtms.gtms.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.gtms.gtms.entity.StudentTeacherRelation;
import lombok.Data;

/**
 * @Author: 84644
 * @Date: 2019/4/25 18:13
 * @Description:
 **/
@Data
public class ThesisInfoDetailVo extends StudentTeacherRelation {
    private String studentMajor;

    private String studentInstructor;

    private String studentPhone;

    private String studentEmail;

    private String teacherTitle;

    private String teacherEducation;

    private String teacherPhone;

    private String teacherEmail;

}
