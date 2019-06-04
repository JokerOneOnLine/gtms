package com.gtms.gtms.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 84644
 * @Date: 2019/4/7 23:31
 * @Description:
 **/
@TableName("T_MENU")
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -224299549704381725L;

    @TableId("id")
    private Integer id;

    @TableField("menu_text")
    private String menuText;

    @TableField("menu_belong")
    private String menuBelong;

    @TableField("menu_status")
    private String menuStatus;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("menu_url")
    private String menuUrl;
}
