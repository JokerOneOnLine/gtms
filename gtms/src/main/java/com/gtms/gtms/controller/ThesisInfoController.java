package com.gtms.gtms.controller;

import com.gtms.gtms.entity.StudentTeacherRelation;
import com.gtms.gtms.entity.TeacherInfo;
import com.gtms.gtms.entity.ThesisInfo;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.service.InitService;
import com.gtms.gtms.service.StudentTeacherRelationService;
import com.gtms.gtms.service.TeacherInfoService;
import com.gtms.gtms.service.ThesisInfoService;
import com.gtms.gtms.util.MailUtils;
import com.gtms.gtms.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: 84644
 * @Date: 2019/4/9 12:05
 * @Description:
 **/
@RequestMapping("/thesis")
@Controller
public class ThesisInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(ThesisInfoController.class);

    @Autowired
    private ThesisInfoService thesisInfoService;

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Autowired
    private StudentTeacherRelationService studentTeacherRelationService;

    @Autowired
    private MailUtils mailUtils;

    @Value("${mail.from.account}")
    private String mailFromAccount;

    private final String MAIL_TITLE = "论文选题教师处理结果";


    @RequestMapping("/getThesisInfo")
    @ResponseBody
    public Object getThesisInfo(int page, int rows) {
        LOG.info("==================开始查询论文信息==================");
        try {
            Map<String, Object> res = thesisInfoService.getThesisInfo(page, rows);
            LOG.info("==================查询论文信息结束，数据：{}==================", res);
            return res;
        } catch (Exception e) {
            LOG.error("==================查询论文信息异常,e={}==================", e);
        }
        return ResultUtil.dataGridEmptyResult();
    }

    @RequestMapping("/getThesisInfoByTeacherNo")
    @ResponseBody
    public Object getThesisInfoByTeacherNo(int page, int rows, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            TeacherInfo teacherInfo = teacherInfoService.getInfo(user.getId());
            LOG.info("==================开始根据教师用户id查询教师发布论文信息，教师用户id：{}==================", user.getId());
            Map<String, Object> res = thesisInfoService.getThesisInfoByTeacherNo(page, rows, teacherInfo.getTeacherNo());
            LOG.info("==================根据教师用户id查询教师发布论文信息完成，数据：{}==================", res);
            return res;
        } catch (Exception e) {
            LOG.error("==================根据教师用户id查询教师发布论文信息异常，e={}==================", e);
            return ResultUtil.dataGridEmptyResult();
        }
    }

    @RequestMapping("/addThesis")
    @ResponseBody
    public Object addThesis(String thesisTitle, String noticeInfo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        LOG.info("==================开始添加论文信息==================");
        ThesisInfo thesisInfo = new ThesisInfo();
        thesisInfo.setThesisTitle(thesisTitle);
        thesisInfo.setNoticeInfo(noticeInfo);
        try {
            TeacherInfo teacherInfo = teacherInfoService.getInfo(user.getId());
            thesisInfo.setTeacherName(teacherInfo.getTeacherName());
            thesisInfo.setTeacherNo(teacherInfo.getTeacherNo());
            thesisInfoService.insert(thesisInfo);
            LOG.info("==================添加论文信息完成==================");
            return ResultUtil.success("添加论文信息成功");
        } catch (Exception e) {
            LOG.error("添加论文信息异常，e={}", e);
            return ResultUtil.error("添加论文信息异常");
        }
    }

    @RequestMapping("/modifyThesis")
    @ResponseBody
    public Object modifyThesis(ThesisInfo thesisInfo) {
        LOG.info("==================开始修改论文信息==================");
        try {
            thesisInfoService.updateById(thesisInfo);
            LOG.info("==================修改论文信息完成==================");
            return ResultUtil.success("修改论文信息成功");
        } catch (Exception e) {
            LOG.error("修改论文信息异常，e={}", e);
            return ResultUtil.error("修改论文信息异常");
        }
    }

    @RequestMapping("/getThesisInfoById")
    @ResponseBody
    public Object getThesisInfoById(Integer id) {
        LOG.info("==================开始根据id查询论文信息==================");
        try {
            ThesisInfo thesisInfo = thesisInfoService.selectById(id);
            LOG.info("==================根据id查询论文信息完成，论文信息：{}==================", thesisInfo);
            return ResultUtil.success(thesisInfo);
        } catch (Exception e) {
            LOG.error("修改论文信息异常，e={}", e);
            return ResultUtil.error("修改论文信息异常");
        }
    }

    @RequestMapping("/deleteThesisInfoByIds")
    @ResponseBody
    @Transactional
    public Object deleteThesisInfoByIds(Integer[] ids) {
        LOG.info("==================开始根据id删除论文信息==================");
        try {
            // 删除论文信息
            thesisInfoService.deleteBatchIds(Arrays.asList(ids));
            // 删除学生选题信息
            studentTeacherRelationService.deleteByThesisId(ids);
            // 发送邮件通知学生
            List<StudentTeacherRelation> thesisList = studentTeacherRelationService.getInfoByThesisNos(ids);
            for (int i = 0;i<thesisList.size();i++){
                try {
                    mailUtils.sendEmail(mailFromAccount,thesisList.get(i).getStudentEmail(),MAIL_TITLE,
                            "你所选的论文选题已经被教师删除，请及时处理");
                } catch (MailException e) {
                    LOG.error("==================发送邮件失败，e={},收件邮箱：{}==================", e, thesisList.get(i).getStudentEmail());
                }
            }
            LOG.info("==================根据id删除论文信息完成==================");
            return ResultUtil.success("删除成功");
        } catch (Exception e) {
            LOG.error("修改论文信息异常，e={}", e);
            return ResultUtil.error("修改论文信息异常");
        }
    }
}
