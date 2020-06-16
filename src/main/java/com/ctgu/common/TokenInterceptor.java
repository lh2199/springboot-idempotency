package com.ctgu.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ctgu.annotation.ApiIdempotent;
import com.ctgu.service.TokenService;

public class TokenInterceptor implements HandlerInterceptor
{
	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		// return HandlerInterceptor.super.preHandle(request, response, handler);
		if (!(handler instanceof HandlerMethod))
		{
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ApiIdempotent apiIdempotent = handlerMethod.getMethod()
				.getAnnotation(ApiIdempotent.class);
		if (apiIdempotent != null)
		{
			// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回提示
			tokenService.checkToken(request);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
