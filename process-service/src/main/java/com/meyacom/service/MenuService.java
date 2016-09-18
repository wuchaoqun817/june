package com.meyacom.service;

import java.util.List;

import com.meyacom.domain.Menu;

public interface MenuService {


	List<Menu> queryMenuByParentId(String userName, int menuParentId);

}
