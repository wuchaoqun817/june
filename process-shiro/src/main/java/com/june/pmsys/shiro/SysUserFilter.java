package com.june.pmsys.shiro;




import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.june.pmsys.service.UserService;



/**
 * Title: SysUserFilter
 * <p>
 * Description:
 * <p>
 * Copyright: Copyright (c) 2016
 * <p>
 * Company:
 * <p>
 * @author zhoulin.zhu
 * <p>
 *2016年9月2日
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        String username = (String)SecurityUtils.getSubject().getPrincipal();
//        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
