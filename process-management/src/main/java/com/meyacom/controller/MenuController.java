package com.meyacom.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meyacom.domain.Menu;
import com.meyacom.domain.User;
import com.meyacom.service.MenuService;

/**
 * Title: MenuController
 * <p>
 * Description:菜单管理
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * @author zhoulin.zhu
 * <p>
 *2016年9月14日
 */
@Controller
public class MenuController {
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Resource
	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * 查询出用户的菜单
	 */
	@RequestMapping("/queryMenu")
	@ResponseBody
	private List<Menu> queryMenu(HttpServletRequest request){
		User  user	=(User) request.getSession().getAttribute("user");
		logger.info("查询菜单栏的根节点-------start");
		int menuParentId=0;
		String userName=user.getUserName();
		List<Menu> menuList=menuService.queryMenuByParentId(userName,menuParentId);
		logger.info("查询单栏的根节点-------end");
		logger.info("根据菜单栏的根节点查询树children-------start");
		findTree(userName,menuList);
		logger.info("用户["+userName+"]"+"的菜单为:"+menuList.toString());
		logger.info("根据菜单栏的根节点查询树children-------end");	
		return menuList;		
	}
	 
	/**
	 * 递归查找菜单的子节点
	 * @param userName 用户名
	 * @param menuList 菜单列表
	 */
	private void findTree(String userName, List<Menu> menuList) {
		if(menuList!=null){
			for(Menu menu: menuList){
				List<Menu> menuChildList=menuService.queryMenuByParentId(userName,menu.getId());
				menu.setChildren(menuChildList);
				findTree(userName,menuChildList);
			}
		}
	}


	
}
