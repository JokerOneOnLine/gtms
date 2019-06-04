package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.gtms.gtms.entity.ThesisInfo;
import com.gtms.gtms.mapper.ThesisInfoMapper;
import com.gtms.gtms.service.ThesisInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 84644
 * @Date: 2019/4/9 12:09
 * @Description:
 **/
@Service
public class ThesisInfoServiceImpl extends ServiceImpl<ThesisInfoMapper, ThesisInfo> implements ThesisInfoService {
    @Autowired
    private ThesisInfoMapper thesisInfoMapper;

    @Override
    public Map<String, Object> getThesisInfo(int page, int rows) {
        Map<String, Object> map = Maps.newHashMap();
        Page<ThesisInfo> pageRecord = new Page<>(page, rows);
        List<ThesisInfo> thesisInfoList = thesisInfoMapper.getThesisInfo(pageRecord);
        map.put("total", pageRecord.getTotal());
        map.put("rows", thesisInfoList);
        return map;
    }

    @Override
    public Map<String, Object> getThesisInfoByTeacherNo(int page, int rows, String theacherNo) {
        Map<String, Object> map = Maps.newHashMap();
        Page<ThesisInfo> pageRecord = new Page<>(page, rows);
        List<ThesisInfo> thesisInfoList = thesisInfoMapper.getThesisInfoByTeacherNo(pageRecord,theacherNo);
        map.put("total", pageRecord.getTotal());
        map.put("rows", thesisInfoList);
        return map;
    }
}
