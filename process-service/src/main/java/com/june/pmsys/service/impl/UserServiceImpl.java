package com.june.pmsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.UserMapper;
import com.june.pmsys.domain.User;
import com.june.pmsys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User findByUsername(String userName) {
		return (User)userMapper.findByUsername(userName);
	}
	
	
	public Integer updateUserLoginIpAndUserAgent(String loginIp, String userAgent,String userName) {
		// TODO Auto-generated method stub
		return userMapper.updateUserLoginIpAndUserAgent(loginIp,  userAgent,userName);
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}



	

	
}
