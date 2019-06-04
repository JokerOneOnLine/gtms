package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.Init;

public interface InitService extends IService<Init> {
    Init getInitData();
}
