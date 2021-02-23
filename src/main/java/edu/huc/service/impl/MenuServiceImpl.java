package edu.huc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.huc.bean.Menu;
import edu.huc.dao.MenuMapper;
import edu.huc.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuList(int role) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",role);
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        return menuList;
    }
}
