package com.gtms.gtms.service;

import com.baomidou.mybatisplus.service.IService;
import com.gtms.gtms.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService extends IService<Menu> {
    List<Menu> getMenyBymenuBelong(String menuBelong);

    Map<String, Object> selectAllMenu(Menu menu, int page, int rows);
}
