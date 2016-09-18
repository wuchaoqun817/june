package com.meyacom.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.meyacom.dao.RoleMapper;
import com.meyacom.domain.Role;
import com.meyacom.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleMapper roleMapper;

	

	@Override
	public List<Integer> findRoleIdsByUserId(int userId) {
		// TODO Auto-generated method stub
		return (List<Integer>)roleMapper.findRoleIdsByUserId(userId);
	}

	
	@Override
	public Set<Role> selectRoleByUsername(String currentUserName) {
		// TODO Auto-generated method stub
		return (Set<Role>)roleMapper.selectRoleByUsername(currentUserName);
	}
	
	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}



}
