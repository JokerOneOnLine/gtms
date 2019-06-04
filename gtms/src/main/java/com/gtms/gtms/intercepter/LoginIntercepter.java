package com.gtms.gtms.intercepter;

import com.gtms.gtms.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 84644
 * @Date: 2019/4/7 22:46
 * @Description:
 **/
@Component
public class LoginIntercepter implements HandlerInterceptor {
    /**
     * 日志句柄
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoginIntercepter.class);

    private static final String TO_LOGIN = "/";
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("==================进入登录验证拦截器==================");
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        User user = (User) session.getAttribute("user");
        //如果session中没有user，表示没登陆
        if (user == null){
            LOG.info("==================无登录用户信息，返回登录页面==================");
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            response.sendRedirect("/");
            return false;
        }else {
            LOG.info("==================用户已登录，用户信息：{}==================",user);
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }

}
