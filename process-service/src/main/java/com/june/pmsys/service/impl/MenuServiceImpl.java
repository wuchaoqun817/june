package com.june.pmsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.MenuDao;
import com.june.pmsys.domain.Menu;
import com.june.pmsys.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao;

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao MenuDao) {
		this.menuDao = MenuDao;
	}

	@Override
	public List<Menu> queryMenuByParentId(String userName, int menuParentId) {
		return (List<Menu>)menuDao.queryMenuByParentId(userName,menuParentId);
	}

	

}
