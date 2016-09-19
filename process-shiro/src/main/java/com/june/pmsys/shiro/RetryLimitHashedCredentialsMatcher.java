package com.june.pmsys.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Title: RetryLimitHashedCredentialsMatcher
 * <p>
 * Description:统计登录失败次数
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * @author zhoulin.zhu
 * <p>
 *2016年9月5日
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(userName);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userName, retryCount);
        }
        System.out.println("用户["+userName+"]累计登录失败次数为："+retryCount);
        if(retryCount.incrementAndGet() > 5) {
            System.out.println("用户["+userName+"]累计登录失败次数超过最大失败次数(5)次，请10分钟之后再试！");

            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            passwordRetryCache.remove(userName);
            System.out.println("用户["+userName+"]清除登录失败次数成功");
        }
        return matches;
    }
}
