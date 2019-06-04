package com.gtms.gtms.vo;

import lombok.Data;

/**
 * @Author: 84644
 * @Date: 2019/4/27 20:55
 * @Description:
 **/
@Data
public class DataAnalysis {
    /**
     * 审核通过
     */
    private int passNum;

    /**
     * 审核未通过
     */
    private int noPassNum;

    /**
     * 审核中
     */
    private int underPassNum;
}
