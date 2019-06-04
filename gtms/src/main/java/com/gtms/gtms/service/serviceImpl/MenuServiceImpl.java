package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.gtms.gtms.entity.Menu;
import com.gtms.gtms.entity.ThesisInfo;
import com.gtms.gtms.mapper.MenuMapper;
import com.gtms.gtms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 84644
 * @Date: 2019/4/7 23:38
 * @Description:
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenyBymenuBelong(String menuBelong) {
        return menuMapper.getMenyBymenuBelong(menuBelong);
    }

    @Override
    public Map<String, Object> selectAllMenu(Menu menu, int page, int rows) {
        Map<String, Object> map = Maps.newHashMap();
        Page<Menu> pageRecord = new Page<>(page, rows);
        List<Menu> menuList = menuMapper.selectAllMenu(menu, pageRecord);
        map.put("total", pageRecord.getTotal());
        map.put("rows", menuList);
        return map;
    }

}
