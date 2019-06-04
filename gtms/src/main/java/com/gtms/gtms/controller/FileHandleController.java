package com.gtms.gtms.controller;

import com.gtms.gtms.entity.StudentTeacherRelation;
import com.gtms.gtms.service.StudentTeacherRelationService;
import com.gtms.gtms.util.FTPUtil;
import com.gtms.gtms.util.MailUtils;
import com.gtms.gtms.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author: 84644
 * @Date: 2019/4/15 20:16
 * @Description:
 **/
@Controller
@RequestMapping("/file")
public class FileHandleController {

    private final Logger LOG = LoggerFactory.getLogger(FileHandleController.class);

    private final String TASK_PATH = "task";

    private final String THESIS_PATH = "thesis";

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.userName}")
    private String ftpUserName;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.basePath}")
    private String basePath;

    @Value("${mail.from.account}")
    private String mailFromAccount;

    @Autowired
    private StudentTeacherRelationService studentTeacherRelationService;

    @Autowired
    private MailUtils mailUtils;

    /**
     * @param multipartFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadTask", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadTask(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return ResultUtil.success("上传失败，请选择文件后上传！");
        }
        LOG.info("==================开始上传单个文件==================");
        // 获得原始后缀
        String suf = multipartFile.getOriginalFilename().split("\\.")[1];
        String thesisNo = request.getParameter("thesisNo");
        String thesisTitle = request.getParameter("thesisTitle");
        String fileName = thesisNo + "." + suf;
        try {
            boolean flag = FTPUtil.uploadFile(ftpHost, ftpPort, ftpUserName, ftpPassword, basePath, TASK_PATH, fileName, multipartFile.getInputStream());
            // 文件上传成功
            if (flag) {
                LOG.info("==================文件上传成功==================");
                // 修改数据库
                studentTeacherRelationService.updateTaskUrlByThesisNo(fileName, thesisNo);
                StudentTeacherRelation studentTeacherRelation = studentTeacherRelationService.getInfoByThesisNo(thesisNo);
                mailUtils.sendEmail(mailFromAccount,studentTeacherRelation.getStudentEmail(),"通知","你所选取的论文《"+thesisTitle+"》教师已经上传模板");
                return ResultUtil.success("文件上传成功！");
            }
        } catch (FileNotFoundException e) {
            LOG.info("==================文件上传异常，e={}==================", e);
        } catch (IOException e) {
            LOG.info("文件上传异常，e={}", e);
        }
        return ResultUtil.error("文件上传异常！");
    }

    @RequestMapping(value = "/uploadThesis", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadThesis(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return ResultUtil.success("上传失败，请选择文件后上传！");
        }
        LOG.info("==================开始上传单个文件==================");
        // 获得原始后缀
        String suf = multipartFile.getOriginalFilename().split("\\.")[1];
        String thesisNo = request.getParameter("thesisNo");
        String thesisTitle = request.getParameter("thesisTitle");
        String fileName = thesisNo + "." + suf;
        try {
            boolean flag = FTPUtil.uploadFile(ftpHost, ftpPort, ftpUserName, ftpPassword, basePath, THESIS_PATH, fileName, multipartFile.getInputStream());
            // 文件上传成功
            if (flag) {
                LOG.info("==================文件上传成功==================");
                // 修改数据库
                studentTeacherRelationService.updateThesisUrlByThesisNo(fileName, thesisNo);
                StudentTeacherRelation studentTeacherRelation = studentTeacherRelationService.getInfoByThesisNo(thesisNo);
                mailUtils.sendEmail(mailFromAccount,studentTeacherRelation.getTeacherEmail(),"通知","你所发布的论文《"+thesisTitle+"》学生已经上传论文");
                return ResultUtil.success("文件上传成功！");
            }
        } catch (FileNotFoundException e) {
            LOG.info("==================文件上传异常，e={}==================", e);
        } catch (IOException e) {
            LOG.info("文件上传异常，e={}", e);
        }
        return ResultUtil.error("文件上传异常！");
    }

    @RequestMapping(value = "/downloadTask")
    public void uploadTask(@RequestParam("thesisNo") String thesisNo, HttpServletResponse response) {
        try {
            StudentTeacherRelation studentTeacherRelation = studentTeacherRelationService.getInfoByThesisNo(thesisNo);
            String fileName = studentTeacherRelation.getTaskUrl();
            LOG.info("==================开始下载文件，远程路径：{}==================", basePath + TASK_PATH + "/" + fileName);
            boolean flag = FTPUtil.downloadFileByPrintWriter(ftpHost, ftpPort, ftpUserName, ftpPassword, basePath + TASK_PATH, fileName, response);
            if (flag){
                mailUtils.sendEmail(mailFromAccount,studentTeacherRelation.getTeacherEmail(),"通知","你所发布的论文《"+studentTeacherRelation.getThesisTitle()+"》学生已经下载模板");
            }
            LOG.info("==================文件下载成功==================");
        } catch (Exception e) {
            LOG.info("==================文件下载异常，e={}==================", e);
        }
    }

    @RequestMapping(value = "/downloadThesis")
    public void uploadThesis(@RequestParam("thesisNo") String thesisNo, HttpServletResponse response) {
        try {
            StudentTeacherRelation studentTeacherRelation = studentTeacherRelationService.getInfoByThesisNo(thesisNo);
            String fileName = studentTeacherRelation.getTaskUrl();
            LOG.info("==================开始下载文件，远程路径：{}==================", basePath + THESIS_PATH + "/" + fileName);
            boolean flag = FTPUtil.downloadFileByPrintWriter(ftpHost, ftpPort, ftpUserName, ftpPassword, basePath + THESIS_PATH, fileName, response);
            if (flag){
                mailUtils.sendEmail(mailFromAccount,studentTeacherRelation.getStudentEmail(),"通知","你所选取的论文《"+studentTeacherRelation.getThesisTitle()+"》老师已经下载论文");
            }
            LOG.info("==================文件下载成功==================");
        } catch (Exception e) {
            LOG.info("==================文件下载异常，e={}==================", e);
        }
    }
}
