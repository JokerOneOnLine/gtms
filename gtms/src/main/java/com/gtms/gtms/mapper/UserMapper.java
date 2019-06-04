package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gtms.gtms.entity.User;
import com.gtms.gtms.vo.ModifyPwdVo;
import org.apache.ibatis.annotations.Param;


/**
 * @Description
 * @auther yinxiongbiao
 * @create 2019-03-31 20:37
 */
public interface UserMapper extends BaseMapper<User> {
    User getUserByAccountAndType(@Param("md") User user);

    int modifyPwd(@Param("md") ModifyPwdVo modifyPwdVo);

}
