package com.june.pmsys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.june.pmsys.common.GetIpAddress;
import com.june.pmsys.service.UserService;
import com.june.pmsys.shiro.PasswordHelper;


/**
 * Title: MainController
 * <p>
 * Description:
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * 
 * @author zhoulin.zhu
 *         <p>
 *         2016年9月5日
 */
@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 进入主界面
	 */
	@RequestMapping("/index")
	private String index() {
		logger.info("进入index界面");
		return "index";
	}

	/**
	 * 进入登录界面
	 */
	@RequestMapping("/login")
	private String login() {
		logger.info("进入login界面");
		return "login";
	}

	/**
	 * 账户已在其他地方登录
	 */
	// @RequestMapping("/kickout")
	// private String kickout() {
	// Subject subject = SecurityUtils.getSubject();
	// String msg="用户["+subject.getPrincipal()+"]已在其他地方登录，请重新登录！";
	// logger.info(msg);
	// return "kickout";
	// }

	/**
	 * test@RequiresPermissions
	 */
	// @RequiresPermissions(value={"role:update,role:create"},logical=Logical.OR)
	// @RequiresPermissions(value = {"user:update" })
	// @RequiresPermissions("/user")
	// @RequestMapping("/user")
	// private String user() {
	// logger.info("进入user界面");
	// Subject subject = SecurityUtils.getSubject();
	// boolean istrue=subject.isPermitted("user:update");
	// System.out.println("------------------------------user:updat:"
	// + istrue);
	// return "user";
	// }

	/**
	 * 登录界面判断
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param rememberMe
	 *            是否是记住我
	 */
	@RequestMapping("/doLogin")
	private String doLogin(@RequestHeader("User-Agent") String userAgent,
			HttpServletRequest request, String userName, String password,
			boolean rememberMe) {
		logger.info("用户[" + userName + "]进入dologin方法！");
		String msg = "";
		String loginIp = GetIpAddress.getIpAddress(request);
		System.out.println("用户的ip地址为：" + loginIp);// 防止使用代理
		System.out.println("用户访问工具为：User_Agent:" + userAgent);
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			//进入登录验证UserRealm doGetAuthenticationInfo
			subject.login(token);

			if (subject.isAuthenticated()) {
				// 登录成功，数据库记录登录时间及访问工具
				Integer result = userService.updateUserLoginIpAndUserAgent(
						loginIp, userAgent, userName);
				if(result==1){
					logger.info("更新用户["+userName+"]登录IP成功！");
				}

				return "index";
			} else {
				return "login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "用户[" + userName + "]:" + "登录密码错误. Password for account "
					+ token.getPrincipal() + " was incorrect.";
			logger.debug(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "用户[" + userName + "]:" + "登录失败次数超过最大系统次数，请稍后再试！";
			logger.debug(msg);
		} catch (LockedAccountException e) {
			msg = "用户[" + userName + "]:" + "帐号已被锁定. The account for username "
					+ token.getPrincipal() + " was locked.";
			logger.debug(msg);
		} catch (DisabledAccountException e) {
			msg = "用户[" + userName + "]:" + "帐号已被禁用. The account for username "
					+ token.getPrincipal() + " was disabled.";
			logger.debug(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "用户[" + userName + "]:" + "帐号已过期. the account for username "
					+ token.getPrincipal() + "  was expired.";
			logger.debug(msg);
		} catch (UnknownAccountException e) {
			msg = "用户[" + userName + "]:"
					+ "帐号不存在. There is no user with username of "
					+ token.getPrincipal();
			logger.debug(msg);
		} catch (UnauthorizedException e) {
			msg = "用户[" + userName + "]:" + "您没有得到相应的授权！" + e.getMessage();
			logger.debug(msg);

		}
		return "login";
	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/logOut")
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		if (subject.isAuthenticated()) {
			logger.debug("用户[" + userName + "]退出登录");
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
	}

	

}
