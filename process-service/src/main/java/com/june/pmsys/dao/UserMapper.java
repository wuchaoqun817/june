package com.june.pmsys.dao;

import org.apache.ibatis.annotations.Param;

import com.june.pmsys.domain.User;

public interface UserMapper {
    

	User findByUsername(@Param("userName")String userName);

	Integer updateUserLoginIpAndUserAgent(@Param("loginIp")String loginIp,@Param("userAgent") String userAgent,@Param("userName") String userName);
}