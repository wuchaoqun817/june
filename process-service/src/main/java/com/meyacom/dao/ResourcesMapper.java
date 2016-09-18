package com.meyacom.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.meyacom.domain.Resources;

public interface ResourcesMapper {
	
	Set<Resources> selectResourceByUsername(@Param("currentUserName")	String currentUserName);

}