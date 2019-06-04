package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gtms.gtms.entity.Init;

public interface InitMapper extends BaseMapper<Init> {
    Init getInitData();
}
