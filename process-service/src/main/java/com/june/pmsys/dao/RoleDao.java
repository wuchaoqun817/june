package com.june.pmsys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.june.pmsys.domain.Role;

public interface RoleDao {

	List<Integer> findRoleIdsByUserId(@Param("userId") int userId);

	Set<Role> selectRoleByUsername(@Param("currentUserName")String currentUserName);
}