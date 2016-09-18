package com.meyacom.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meyacom.dao.MenuMapper;
import com.meyacom.domain.Menu;
import com.meyacom.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuMapper menuMapper;

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public List<Menu> queryMenuByParentId(String userName, int menuParentId) {
		return (List<Menu>)menuMapper.queryMenuByParentId(userName,menuParentId);
	}

	

}
