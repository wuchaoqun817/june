package com.june.pmsys.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.june.pmsys.dao.ResourcesMapper;
import com.june.pmsys.domain.Resources;
import com.june.pmsys.service.ResourcesService;


@Service
public class ResourcesServiceImpl implements ResourcesService{
	
	@Resource
	private ResourcesMapper resourcesMapper;


	@Override
	public Set<Resources> selectResourceByUsername(String currentUserName) {
		return (Set<Resources> )resourcesMapper.selectResourceByUsername(currentUserName);
	}
	

	
	public ResourcesMapper getResourcesMapper() {
		return resourcesMapper;
	}

	public void setResourcesMapper(ResourcesMapper resourcesMapper) {
		this.resourcesMapper = resourcesMapper;
	}

	





	
}
