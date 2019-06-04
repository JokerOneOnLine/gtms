package com.gtms.gtms.controller;

import com.google.common.collect.Maps;
import com.gtms.gtms.entity.StudentInfo;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.service.StudentInfoService;
import com.gtms.gtms.service.TeacherInfoService;
import com.gtms.gtms.util.ResultUtil;
import com.gtms.gtms.vo.ModifyInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: 84644
 * @Date: 2019/4/8 11:07
 * @Description:
 **/
@Controller
@RequestMapping("/person")
public class PersonInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Autowired
    private StudentInfoService studentInfoService;
    /**
     * 获取登录用户的信息
     * @param request
     * @return
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public Object getPersonInfo(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        LOG.info("==================开始查询登录用户信息，登录用户：{}==================",user);
        Map<String,Object> res = Maps.newHashMap();
        try{
            // 登录用户是学生
            if (1 == user.getUserType()){
                StudentInfo studentInfo = studentInfoService.getInfo(user.getId());
                res.put("personInfo",studentInfo);
            }else{
                // 登录用户是老师或者管理员
                TeacherInfo teacherInfo = teacherInfoService.getInfo(user.getId());
                res.put("personInfo",teacherInfo);
            }
            res.put("user",user);
            LOG.info("==================开始查询登录用户信息，登录用户：{}==================",user);
            return ResultUtil.success(res);
        }catch (Exception e){
            LOG.error("==================查询登录用户信息异常，e：{}==================",e);
            return ResultUtil.error("查询登录用户信息异常");
        }
    }

    @RequestMapping("/modifyInfo")
    @ResponseBody
    public Object modifyInfo(ModifyInfoVo modifyInfoVo,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        modifyInfoVo.setUserId(user.getId());
        LOG.info("==================开始修改用户信息==================");
        try {
            if (1 == user.getUserType()){
                studentInfoService.modifyInfo(modifyInfoVo);
            }else {
                // 管理员或教师角色的修改信息
                teacherInfoService.modifyInfo(modifyInfoVo);
            }
        }catch (Exception e){
            LOG.error("==================修改用户信息失败，e={}==================",e);
            return ResultUtil.error("修改用户信息异常");
        }
        LOG.info("==================修改用户信息成功==================");
        return ResultUtil.success("修改用户信息成功");
    }
}
