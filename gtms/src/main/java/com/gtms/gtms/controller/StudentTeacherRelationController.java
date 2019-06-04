package com.gtms.gtms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gtms.gtms.entity.*;
import com.gtms.gtms.service.*;
import com.gtms.gtms.util.MailUtils;
import com.gtms.gtms.util.ResultUtil;
import com.gtms.gtms.vo.DataAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: 84644
 * @Date: 2019/4/10 9:15
 * @Description:
 **/
@RequestMapping("/studentTeacherRelation")
@Controller
public class StudentTeacherRelationController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentTeacherRelationController.class);

    @Autowired
    private StudentTeacherRelationService studentTeacherRelationService;

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private InitService initService;

    @Autowired
    private ThesisInfoService thesisInfoService;

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Autowired
    private NoPassThesisService noPassThesisService;

    @Autowired
    private MailUtils mailUtils;

    private final String AGREE = "1";

    private final String DISAGREE = "2";

    private final String MAIL_TITLE = "论文选题教师处理结果";

    @Value("${mail.from.account}")
    private String mailFromAccount;

    /**
     * 学生选题
     *
     * @param thesisId
     * @param request
     * @return
     */
    @RequestMapping("/getStudentSelectThesisResult")
    @ResponseBody
    @Transactional
    public Object getInfoByStudentNo(String thesisId, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        int studentNum;
        try {
            Init init = initService.getInitData();
            studentNum = init.getStudentNum();
            StudentInfo studentInfo = studentInfoService.getInfo(u.getId());
            LOG.info("==================开始根据学生学号查询学生选题信息，学生学号：{}==================", studentInfo.getStudentNo());
            List<StudentTeacherRelation> res = studentTeacherRelationService.getInfoByStudentNo(studentInfo.getStudentNo());
            LOG.info("==================根据学生学号查询学生选题信息完成，数据：{}==================", res);
            // 选题数量达到了系统设定值
            if (res.size() >= studentNum) {
                return ResultUtil.success("选题数已经达到最大值，不能再选！");
            }
            ThesisInfo thesisInfo = thesisInfoService.selectById(thesisId);
            if (thesisInfo.getSelectNum() != 0) {
                return ResultUtil.success("该选题已经被选，不能选择该选题！");
            }
            StudentTeacherRelation studentTeacherRelation = new StudentTeacherRelation();
            studentTeacherRelation.setStudentName(studentInfo.getStudentName());
            studentTeacherRelation.setStudentNo(studentInfo.getStudentNo());
            studentTeacherRelation.setStudentClass(studentInfo.getStudentClass());
            studentTeacherRelation.setTeacherName(thesisInfo.getTeacherName());
            studentTeacherRelation.setTeacherNo(thesisInfo.getTeacherNo());
            studentTeacherRelation.setThesisNo(thesisInfo.getId().toString());
            studentTeacherRelation.setThesisTitle(thesisInfo.getThesisTitle());
            studentTeacherRelationService.insert(studentTeacherRelation);
            LOG.info("==================选题成功==================");
            // 选题成功，论文被选择数累加
            thesisInfo.setSelectNum(thesisInfo.getSelectNum() + 1);
            thesisInfoService.updateById(thesisInfo);
            return ResultUtil.success("选题成功");
        } catch (Exception e) {
            LOG.error("==================根据学生学号查询学生选题信息异常，e={}==================", e);
            return ResultUtil.error("根据学生学号查询学生选题信息异常");
        }
    }

    /**
     * 根据学号查询学生选题信息
     */
    @RequestMapping("/getStudentSelectThesisByStudentNo")
    @ResponseBody
    public Object getStudentSelectThesisByStudentNo(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        try {
            StudentInfo studentInfo = studentInfoService.getInfo(u.getId());
            LOG.info("==================开始根据学号查询学生选题信息，学号：{}==================", studentInfo.getStudentNo());
            List<StudentTeacherRelation> res = studentTeacherRelationService.getInfoByStudentNo(studentInfo.getStudentNo());
            res.forEach(item -> {
                item.setOpinionFlagStr(item.getOpinionFlag() == null ? "未审核" : (item.getOpinionFlag() == 1 ? "同意" : "未审核"));
                item.setTeacherOpinion(item.getTeacherOpinion() == null ? "" : item.getTeacherOpinion());
            });
            LOG.info("==================根据学号查询学生选题信息完成，选题信息：{}==================", res);
            return ResultUtil.success(res);
        } catch (Exception e) {
            LOG.error("==================查询学生选题信息异常==================");
            return ResultUtil.error("查询学生选题信息异常");
        }
    }

    /**
     * 根据教师id查询
     *
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/getStudentSelectThesisByTeacherNo")
    @ResponseBody
    public Object getStudentSelectThesisByTeacherNo(int page, int rows, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        TeacherInfo teacherInfo = teacherInfoService.getInfo(user.getId());
        try {
            LOG.info("==================开始根据教师编号查询学生选题信息==================");
            Map<String, Object> res = studentTeacherRelationService.getInfoByTeacherNo(page, rows, teacherInfo.getTeacherNo());
            LOG.info("==================根据教师id查询学生选题信息完成,信息：{}==================", res);
            return res;
        } catch (Exception e) {
            LOG.error("==================根据教师id查询学生选题信息失败，e={}==================", e);
            return ResultUtil.dataGridEmptyResult();
        }
    }

    /**
     * @param opinionFlag    意见标识1：同意，2：不同意
     * @param teacherOpinion 老师意见
     * @param thesisNo       论文id
     * @return
     */
    @RequestMapping("/operate")
    @ResponseBody
    @Transactional
    public Object operate(String opinionFlag, String teacherOpinion, String thesisNo) {
        LOG.info("==================开始处理论文意见==================");
        StudentTeacherRelation studentTeacherRelation = studentTeacherRelationService.getInfoByThesisNo(thesisNo);
        StringBuilder sb = new StringBuilder();
        try {
            // 教师同意选题
            if (AGREE.equals(opinionFlag)) {
                // 根据论文id更新关系表
                studentTeacherRelationService.updateByThesisNo(thesisNo, opinionFlag, teacherOpinion);
                // 发送邮件通知学生
                try {
                    mailUtils.sendEmail(mailFromAccount, studentTeacherRelation.getStudentEmail(), MAIL_TITLE,
                            "您选取的论文已经被教师审核通过！");
                    sb.append("邮件发送成功！");
                } catch (MailException e) {
                    LOG.error("==================邮件发送失败==================");
                    sb.append("邮件发送失败！");
                }
            } else {
                // 教师不同意选题
                // 删除关系表对应数据
                Integer[] thesisNos = {Integer.parseInt(thesisNo)};
                studentTeacherRelationService.deleteByThesisId(thesisNos);
                // 插入新数据到不通过论文信息表
                NoPassThesis noPassThesis = new NoPassThesis();
                studentTeacherRelation.setTeacherOpinion(teacherOpinion);
                BeanUtils.copyProperties(studentTeacherRelation, noPassThesis);
                noPassThesisService.insert(noPassThesis);
                // 更新论文信息表，设置论文选题数量-1
                ThesisInfo thesisInfo = thesisInfoService.selectById(studentTeacherRelation.getThesisNo());
                thesisInfo.setSelectNum(thesisInfo.getSelectNum() - 1);
                thesisInfoService.updateById(thesisInfo);
                // 发送邮件通知学生
                try {
                    mailUtils.sendEmail(mailFromAccount, studentTeacherRelation.getStudentEmail(), MAIL_TITLE, "很遗憾，你选择的论文审核未通过，请及时处理");
                    sb.append("邮件发送成功！");
                } catch (MailException e) {
                    LOG.error("==================邮件发送失败，e={}==================", e);
                    sb.append("邮件发送失败！");
                }
            }
            return ResultUtil.success("操作成功！" + sb.toString());
        } catch (Exception e) {
            LOG.error("==================操作失败，e={}==================", e);
            return ResultUtil.error("操作失败");
        }
    }

    @RequestMapping("/getAgreeThesisByTeacherNo")
    @ResponseBody
    public Object getAgreeThesisByTeacherNo(int page, int rows, HttpServletRequest request) {
        LOG.info("==================开始查询审核通过的论文信息==================");
        User user = (User) request.getSession().getAttribute("user");
        try {
            TeacherInfo teacherInfo = teacherInfoService.getInfo(user.getId());
            Map<String, Object> res = studentTeacherRelationService.getAgreeThesisByTeacherNo(page, rows, teacherInfo.getTeacherNo());
            LOG.info("==================查询审核通过的论文信息结束，信息：{}==================", res);
            return res;
        } catch (Exception e) {
            LOG.error("查询审核通过的论文信息异常，e={}", e);
            return ResultUtil.dataGridEmptyResult();
        }
    }

    @RequestMapping("/getAgreeThesisByStudentNo")
    @ResponseBody
    public Object getAgreeThesisByStudentNo(int page, int rows, HttpServletRequest request) {
        LOG.info("==================开始查询审核通过的论文信息==================");
        User user = (User) request.getSession().getAttribute("user");
        try {
            StudentInfo studentInfo = studentInfoService.getInfo(user.getId());
            Map<String, Object> res = studentTeacherRelationService.getAgreeThesisByStudentNo(page, rows, studentInfo.getStudentNo());
            LOG.info("==================查询审核通过的论文信息结束，信息：{}==================", res);
            return res;
        } catch (Exception e) {
            LOG.error("查询审核通过的论文信息异常，e={}", e);
            return ResultUtil.dataGridEmptyResult();
        }
    }

    @RequestMapping("/getThesisInfoDetail")
    @ResponseBody
    public Object getThesisInfoDetail(StudentTeacherRelation studentTeacherRelation, int page, int rows) {
        LOG.info("==================开始查询论文情况信息==================");
        try {
            return studentTeacherRelationService.getThesisInfoDetail(studentTeacherRelation, page, rows);
        } catch (Exception e) {
            LOG.error("==================查询论文情况信息异常，e={}==================", e);
        }
        return ResultUtil.dataGridEmptyResult();
    }

    @RequestMapping("/getEchartData")
    @ResponseBody
    public Object getEchartData() {
        //['选题审核通过','选题未审核','选题未通过']

        DataAnalysis dataAnalysis = new DataAnalysis();
        try {
            LOG.info("==================开始查询图标数据==================");
            int passNum = studentTeacherRelationService.selectPassNum();
            dataAnalysis.setPassNum(passNum);

            dataAnalysis.setUnderPassNum(studentTeacherRelationService.selectUnderPassNumAndPassNum() - passNum);

            dataAnalysis.setNoPassNum(studentTeacherRelationService.selectNoPassNum());
        } catch (Exception e) {
            LOG.error("==================查询图表数据异常，e={}==================", e);
        }
        return ResultUtil.success(dataAnalysis);
    }
}
