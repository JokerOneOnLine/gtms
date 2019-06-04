package com.gtms.gtms.controller;

import com.gtms.gtms.entity.Init;
import com.gtms.gtms.service.InitService;
import com.gtms.gtms.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 84644
 * @Date: 2019/4/10 8:40
 * @Description:
 **/
@RequestMapping("/init")
@Controller
public class InitController {
    private static final Logger LOG = LoggerFactory.getLogger(InitController.class);

    @Autowired
    private InitService initService;

    @RequestMapping("/getInitData")
    @ResponseBody
    public Object getInitData() {
        LOG.info("==================开始查询系统初始化数据==================");
        try {
            Init init = initService.getInitData();
            LOG.info("==================查询系统初始化数据完成，数据：{}==================", init);
            return ResultUtil.success(init);
        } catch (Exception e) {
            LOG.error("==================查询系统初始化数据异常==================");
            return ResultUtil.error("查询系统初始化数据异常");
        }
    }
    @RequestMapping("/modifyInitData")
    @ResponseBody
    public Object modifyInitData(Init init) {
        LOG.info("==================开始查询系统初始化数据==================");
        try {
            Init data = initService.getInitData();
            data.setStudentNum(init.getStudentNum());
            data.setTeacherNum(init.getTeacherNum());
            initService.updateById(data);
            LOG.info("==================查询系统初始化数据完成，数据：{}==================", init);
            return ResultUtil.success(init);
        } catch (Exception e) {
            LOG.error("==================查询系统初始化数据异常==================");
            return ResultUtil.error("查询系统初始化数据异常");
        }
    }

}
