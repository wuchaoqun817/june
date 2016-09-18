package com.meyacom.service;

import java.util.List;
import java.util.Set;

import com.meyacom.domain.Role;

public interface RoleService {

	List<Integer> findRoleIdsByUserId(int userId);

	Set<Role> selectRoleByUsername(String currentUserName);

}
