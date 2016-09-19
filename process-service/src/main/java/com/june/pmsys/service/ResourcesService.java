package com.june.pmsys.service;

import java.util.List;
import java.util.Set;

import com.june.pmsys.domain.Resources;

public interface ResourcesService {

	Set<Resources> selectResourceByUsername(String currentUserName);


}
