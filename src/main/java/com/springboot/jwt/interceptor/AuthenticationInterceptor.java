package com.springboot.jwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.springboot.jwt.annotation.Token;
import com.springboot.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 拦截器
 *
 * @author AuroraLove
 * @date 2019/3/29
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有token注释，有则认证
        if (method.isAnnotationPresent(Token.class)) {
            Token tokenAnnotation = method.getAnnotation(Token.class);
            if (tokenAnnotation.required()) {
                //从请求头中取出token参数
                String token = httpServletRequest.getHeader("token");
                if (token == null){
                    throw new Exception("token信息不存在！");
                }
                //解析token参数
                Map<String, Claim> claims = JWT.decode(token).getClaims();
                Long id = claims.get("id").asLong();
                //验证用户是否存在
                if (userService.verify(id)){
                    return true;
                }
                throw new Exception("token信息验证失败");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
