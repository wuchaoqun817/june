package com.june.pmsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.UserDao;
import com.june.pmsys.domain.User;
import com.june.pmsys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User findByUsername(String userName) {
		return (User)userDao.findByUsername(userName);
	}
	
	
	public Integer updateUserLoginIpAndUserAgent(String loginIp, String userAgent,String userName) {
		// TODO Auto-generated method stub
		return userDao.updateUserLoginIpAndUserAgent(loginIp,  userAgent,userName);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao UserDao) {
		this.userDao = UserDao;
	}



	

	
}
