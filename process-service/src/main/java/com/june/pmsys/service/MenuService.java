package com.june.pmsys.service;

import java.util.List;

import com.june.pmsys.domain.Menu;

public interface MenuService {


	List<Menu> queryMenuByParentId(String userName, int menuParentId);

}
