package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gtms.gtms.entity.Init;
import com.gtms.gtms.mapper.InitMapper;
import com.gtms.gtms.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 84644
 * @Date: 2019/4/10 8:25
 * @Description:
 **/
@Service
public class InitServiceImpl extends ServiceImpl<InitMapper, Init> implements InitService {

    @Autowired
    private InitMapper initMapper;

    @Override
    public Init getInitData() {
        return initMapper.getInitData();
    }
}
