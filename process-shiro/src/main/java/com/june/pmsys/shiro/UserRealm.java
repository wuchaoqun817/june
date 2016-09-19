package com.june.pmsys.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.june.pmsys.domain.Role;
import com.june.pmsys.domain.User;
import com.june.pmsys.service.ResourcesService;
import com.june.pmsys.service.RoleService;
import com.june.pmsys.service.UserService;

/**
 * Title: UserRealm
 * <p>
 * Description:登陆认证和授权
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * 
 * @author zhoulin.zhu
 *         <p>
 *         2016年9月2日
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private ResourcesService resourcesService;

	/**
	 * 权限验证,授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 根据用户配置用户与权限
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}
		String currentUserName = (String) getAvailablePrincipal(principals);

		// 角色名称
		Set<String> roleName = new HashSet<String>();

		Set<String> permissions = new HashSet<String>();
		// 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);

		if (null != currentUserName) {
			Set<com.june.pmsys.domain.Resources> resources = resourcesService
					.selectResourceByUsername(currentUserName);
			Set<com.june.pmsys.domain.Role> roles = roleService
					.selectRoleByUsername(currentUserName);
			for (Role role : roles) {
				roleName.add(String.valueOf(role.getRoleName()));
			}
			for (com.june.pmsys.domain.Resources resource : resources) {
				// 使用url进行匹配
				permissions.add(resource.getResourcesUrl());
				// permissions.add(resource.getResourcespermission());
			}
		} else {
			throw new AuthorizationException();
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 增加角色机构代码( 参考的为permissions:resource id )
		info.addRoles(roleName);
		System.out.println("用户" + currentUserName + "拥有的角色编号（）列表为："
				+ roleName.toString());
		info.addStringPermissions(permissions);
		System.out.println("用户" + currentUserName + "拥有的权限（permission）（）列表为："
				+ permissions.toString());
		return info;
	}

	/**
	 * 登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		String userName = (String) token.getPrincipal();
		System.out.println("用户：[" + userName + "]进入登录验证");
		User user = userService.findByUsername(userName);

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if ("0".equals(user.getStatus())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=username+salt
				getName() // realm name
		);
		
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	

	
	
}
