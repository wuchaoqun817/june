package com.meyacom.service;

import com.meyacom.domain.User;

public interface UserService {

	User findByUsername(String username);

	Integer updateUserLoginIpAndUserAgent(String loginIp, String userAgent,String userName);


}
