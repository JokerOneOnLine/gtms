package com.gtms.gtms.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @auther yinxiongbiao
 * @create 2019-03-31 19:32
 */
@TableName("T_USER")
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -630231891815659089L;

    @TableId("id")
    private Integer id;

    @TableField("user_account")
    private String userAccount;

    @TableField("user_password")
    private String userPassword;

    @TableField("user_type")
    private Integer userType;
}
