package com.meyacom.service;

import java.util.List;
import java.util.Set;

import com.meyacom.domain.Resources;

public interface ResourcesService {

	Set<Resources> selectResourceByUsername(String currentUserName);


}
