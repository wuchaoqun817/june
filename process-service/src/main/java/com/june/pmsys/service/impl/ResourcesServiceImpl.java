package com.june.pmsys.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.ResourcesDao;
import com.june.pmsys.domain.Resources;
import com.june.pmsys.service.ResourcesService;


@Service
public class ResourcesServiceImpl implements ResourcesService{
	
	@Resource
	private ResourcesDao resourcesDao;


	@Override
	public Set<Resources> selectResourceByUsername(String currentUserName) {
		return (Set<Resources> )resourcesDao.selectResourceByUsername(currentUserName);
	}
	

	
	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}

	public void setResourcesDao(ResourcesDao ResourcesDao) {
		this.resourcesDao = ResourcesDao;
	}

	





	
}
