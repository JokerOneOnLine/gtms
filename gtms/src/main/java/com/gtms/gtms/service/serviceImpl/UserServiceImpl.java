package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.mapper.UserMapper;
import com.gtms.gtms.service.UserService;
import com.gtms.gtms.vo.ModifyPwdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther yinxiongbiao
 * @create 2019-03-31 20:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByAccountAndType(User user) {
        return userMapper.getUserByAccountAndType(user);
    }

    @Override
    public int modifyPwd(ModifyPwdVo modifyPwdVo) {
        return userMapper.modifyPwd(modifyPwdVo);
    }

    @Override
    public Integer insertReturnId(User user) {
        super.insert(user);
        return user.getId();
    }
}
