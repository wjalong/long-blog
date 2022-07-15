package com.wjalong.blog.config;


import cn.hutool.core.util.ObjectUtil;
import com.wjalong.blog.anno.PassToken;
import com.wjalong.common.exception.BizException;
import com.wjalong.util.DeviceUtils;
import com.wjalong.util.web.SessionUser;
import com.wjalong.util.web.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @Description: 拦截器
 * @author: wangjialong
 * @Date 2021/6/30 14:39
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionUtil sessionUtil;
    @Resource
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        String pageToken = sessionUtil.getTokenByPage(request);

        //如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        //检查是否有passtoken注释，有则跳过认证


        // 执行认证
        if (ObjectUtil.isEmpty(token)) {
            throw new BizException("auth.token_has_exipred", "auth.token_has_exipred");
        }
        if (ObjectUtil.isEmpty(token) && ObjectUtil.isEmpty(pageToken)) {
            // 未登陆
            throw new BizException("auth.token_has_exipred", this.getMessage("auth.token_has_exipred"));
        } else {
            if (ObjectUtil.isNotEmpty(pageToken) && ObjectUtil.isEmpty(token)) {
                token = pageToken;
            }
            SessionUser user = sessionUtil.getUserSession(token);
            if (user == null) {// 没有这个token
                throw new BizException("auth.token_has_exipred", this.getMessage("auth.token_has_exipred"));
            } else {
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public String getMessage(String key, String... args) {
        return this.messageSource.getMessage(key, args, Locale.getDefault());
    }
}

