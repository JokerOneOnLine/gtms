package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.vo.ModifyPwdVo;

/**
 * @Description
 * @auther yinxiongbiao
 * @create 2019-03-31 20:31
 */
public interface UserService extends IService<User> {
    User getUserByAccountAndType(User user);
    int modifyPwd(ModifyPwdVo modifyPwdVo);
    Integer insertReturnId(User user);
}
