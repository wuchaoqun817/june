package com.june.pmsys.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.RoleDao;
import com.june.pmsys.domain.Role;
import com.june.pmsys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;

	

	@Override
	public List<Integer> findRoleIdsByUserId(int userId) {
		// TODO Auto-generated method stub
		return (List<Integer>)roleDao.findRoleIdsByUserId(userId);
	}

	
	@Override
	public Set<Role> selectRoleByUsername(String currentUserName) {
		// TODO Auto-generated method stub
		return (Set<Role>)roleDao.selectRoleByUsername(currentUserName);
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao RoleDao) {
		this.roleDao = RoleDao;
	}



}
