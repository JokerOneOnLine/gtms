package com.gtms.gtms.controller;

import com.gtms.gtms.entity.StudentInfo;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.enumeration.UserType;
import com.gtms.gtms.service.StudentInfoService;
import com.gtms.gtms.service.TeacherInfoService;
import com.gtms.gtms.service.UserService;
import com.gtms.gtms.util.ResultUtil;
import com.gtms.gtms.vo.ModifyPwdVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: 84644
 * @Date: 2019/4/8 22:03
 * @Description:
 **/
@RequestMapping("/user")
@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private TeacherInfoService teacherInfoService;

    @RequestMapping("/modifyPwd")
    @ResponseBody
    public Object modifyPwd(ModifyPwdVo modifyPwdVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (!user.getUserPassword().equals(modifyPwdVo.getPrePwd())) {
            return ResultUtil.success("原密码错误！");
        }
        modifyPwdVo.setUserId(user.getId());
        try {
            LOG.info("==================开始修改用户密码==================");
            userService.modifyPwd(modifyPwdVo);
            LOG.info("==================修改用户密码成功==================");
            return ResultUtil.success("修改用户密码成功");
        } catch (Exception e) {
            LOG.error("==================修改用户密码失败，e={}==================", e);
            return ResultUtil.error("修改用户密码异常");
        }
    }

    /**
     * 账号和密码的初始值都是学号
     *
     * @param studentInfo
     * @return
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    @Transactional
    public Object addStudent(StudentInfo studentInfo) {
        LOG.info("==================开始添加用户信息，用户信息：{}==================", studentInfo);
        try {
            List<User> userList = userService.selectList(null);
            if (userList != null && userList.size() > 0) {
                for (int i = 0; i < userList.size(); i++) {
                    if (StringUtils.equals(userList.get(i).getUserAccount(), studentInfo.getStudentNo())) {
                        return ResultUtil.success("用户信息已经存在！");
                    }
                }
            }
            User user = new User();
            user.setUserAccount(studentInfo.getStudentNo());
            user.setUserPassword(studentInfo.getStudentNo());
            user.setUserType(UserType.STUDENT.getType());
            // 主键返回
            Integer id = userService.insertReturnId(user);
            studentInfo.setUserId(id);
            studentInfoService.insert(studentInfo);
            LOG.info("==================添加用户信息完成==================");
            return ResultUtil.success("添加用户信息成功！");
        } catch (Exception e) {
            LOG.info("==================添加用户信息异常，e={}==================", e);
            return ResultUtil.error("添加用户信息异常！");
        }

    }

    @RequestMapping("/addTeacher")
    @ResponseBody
    @Transactional
    public Object addStudent(TeacherInfo teacherInfo) {
        LOG.info("==================开始添加用户信息，用户信息：{}==================", teacherInfo);
        try {
            List<User> userList = userService.selectList(null);
            if (userList != null && userList.size() > 0) {
                for (int i = 0; i < userList.size(); i++) {
                    if (StringUtils.equals(userList.get(i).getUserAccount(), teacherInfo.getTeacherNo())) {
                        return ResultUtil.success("用户信息已经存在！");
                    }
                }
            }
            User user = new User();
            user.setUserAccount(teacherInfo.getTeacherNo());
            user.setUserPassword(teacherInfo.getTeacherNo());
            user.setUserType(UserType.TEACHER.getType());
            // 主键返回
            Integer id = userService.insertReturnId(user);
            teacherInfo.setUserId(id);
            teacherInfoService.insert(teacherInfo);
            LOG.info("==================添加用户信息完成==================");
            return ResultUtil.success("添加用户信息成功！");
        } catch (Exception e) {
            LOG.info("==================添加用户信息异常，e={}==================", e);
            return ResultUtil.error("添加用户信息异常！");
        }
    }
}
