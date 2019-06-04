package com.gtms.gtms.controller;

import com.gtms.gtms.entity.User;
import com.gtms.gtms.service.UserService;
import com.gtms.gtms.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @auther yinxiongbiao
 * @create 2019-03-31 20:40
 */
@Controller
public class LoginController {
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private static final String LOGIN = "login.html";

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public Object index() {
        return LOGIN;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(User user, HttpServletRequest request) {
        LOG.info("==================开始查询用户信息，用户类型={},用户账号={}==================", user.getUserType(), user.getUserAccount());
        try {
            User u = userService.getUserByAccountAndType(user);
            if (null == u) {
                LOG.info("==================用户信息不存在==================");
                return ResultUtil.error("用户信息不存在");
            } else {
                if (!user.getUserPassword().equals(u.getUserPassword())) {
                    LOG.info("==================用户密码有误==================");
                    return ResultUtil.error("账号或密码错误");
                }
            }
            LOG.info("==================查询用户信息结束==================");
            // 登录成功，将用户信息保存在session
            request.getSession().setAttribute("user", u);
            return ResultUtil.success("登录成功");
        } catch (Exception e) {
            LOG.error("==================查询用户信息异常==================");
            return ResultUtil.error("查询用户信息异常");
        }
    }

    /**
     * 退出登陆
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
    }


    /**
     * 获取登录用户的值
     *
     * @param request
     * @return
     */
    @RequestMapping("/getLoginUserInfo")
    @ResponseBody
    public Object getLoginUserInfo(HttpServletRequest request) {
        return request.getSession().getAttribute("user");
    }
}
