package com.june.pmsys.shiro;



import java.util.HashMap;
import java.util.Map;

import com.june.pmsys.domain.User;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Title: PasswordHelper
 * <p>
 * Description:获取加密之后的密码
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * @author zhoulin.zhu
 * <p>
 *2016年9月2日
 */
@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {
    	
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
    
    /**
     * 根据用户名,密码 获取密码验证的盐值（credentialsSalt=username+salt）
     * @param userName 用户名
     * @param password 密码
     * @return Map
     * 			salt:数据库保存的盐值
     * 			newPassword ：新密码
     **/
    public Map<String,Object> getNewPassword(String userName,String password){
    	HashMap<String,Object> map=new HashMap<String, Object>();
    	String salt=randomNumberGenerator.nextBytes().toHex();
    	String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(userName+salt),
                hashIterations).toHex(); 
    	map.put("salt", salt);
    	map.put("newPassword", newPassword);
    	return map;
    }
}
