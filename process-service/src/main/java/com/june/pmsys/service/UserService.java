package com.june.pmsys.service;

import com.june.pmsys.domain.User;

public interface UserService {

	User findByUsername(String username);

	Integer updateUserLoginIpAndUserAgent(String loginIp, String userAgent,String userName);


}
