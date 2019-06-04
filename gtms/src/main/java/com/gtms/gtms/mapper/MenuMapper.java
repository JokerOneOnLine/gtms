package com.gtms.gtms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gtms.gtms.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenyBymenuBelong(String menuBelong);

    List<Menu> selectAllMenu(@Param("md") Menu menu, Pagination page);

}
