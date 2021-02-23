package edu.huc.service;

import edu.huc.bean.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMenuService {
    List<Menu> getMenuList(int role);
}
