package com.june.pmsys.service;

import java.util.List;
import java.util.Set;

import com.june.pmsys.domain.Role;

public interface RoleService {

	List<Integer> findRoleIdsByUserId(int userId);

	Set<Role> selectRoleByUsername(String currentUserName);

}
