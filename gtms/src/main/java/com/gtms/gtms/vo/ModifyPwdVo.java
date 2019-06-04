package com.gtms.gtms.vo;

import lombok.Data;

/**
 * @Author: 84644
 * @Date: 2019/4/8 22:04
 * @Description:
 **/
@Data
public class ModifyPwdVo {
    private Integer userId;
    private String prePwd;
    private String newPwd;
    private String sureNewPwd;
}
