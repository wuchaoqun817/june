package com.june.pmsys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.june.pmsys.domain.User;
import com.june.pmsys.service.UserService;
import com.june.pmsys.shiro.PasswordHelper;

/**
 * Title: UserController
 * <p>
 * Description:用户管理
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * 
 * @author zhoulin.zhu
 *         <p>
 *         2016年9月8日
 */

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public  User getUser(HttpServletRequest request) {
		logger.info("进入查询用户信息");
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		User user = userService.findByUsername(userName);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		return user;
	}
	
	@RequestMapping("/getUsers")
	@ResponseBody
	public List<User> getUsers(){
		User user=new User();
		user.setPagebegin(1);
		user.setPagesize(2);
		List<User> users= userService.getUsers(user);
		System.err.println(users);
		return users;
	}
	
	
	/**
	 * 测试获取密码加密后的新密码及盐值
	 */
	public static void main(String[] args) {
		Map<String, Object> map = new PasswordHelper().getNewPassword("user",
				"user");
		System.out.println("用户【user】的盐值salt为:" + map.get("salt"));
		System.out.println("用户【user】的盐值newPassword为:" + map.get("newPassword"));
	}
	
}
