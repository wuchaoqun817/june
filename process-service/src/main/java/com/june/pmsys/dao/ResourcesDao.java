package com.june.pmsys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.june.pmsys.domain.Resources;

public interface ResourcesDao {
	
	Set<Resources> selectResourceByUsername(@Param("currentUserName")	String currentUserName);

}