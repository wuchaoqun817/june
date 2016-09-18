package com.meyacom.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.meyacom.domain.Menu;

public interface MenuMapper {

	List<Menu> queryMenuByParentId(@Param("userName")String userName,@Param("menuParentId") int menuParentId);

}
