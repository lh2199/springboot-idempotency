package com.ctgu.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: TokenService
 * @Description: token接口定义
 * @author lh2
 * @date 2020年6月16日 下午4:58:43
 */
public interface TokenService
{
	String createToken();

	void checkToken(HttpServletRequest request);

}
