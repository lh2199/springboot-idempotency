package com.ctgu.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ctgu.common.AppException;
import com.ctgu.service.TokenService;
import com.ctgu.util.StringUtils;

@Service
public class TokenServiceImpl implements TokenService
{
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public String createToken()
	{
		// 通过UUID来生成token
		String tokenValue = "token_" + UUID.randomUUID();
		// 将token放入redis中，设置有效期为60S
		redisTemplate.opsForValue()
				.set(tokenValue, tokenValue, 60, TimeUnit.SECONDS);

		return tokenValue;
	}

	@Override
	public void checkToken(HttpServletRequest request)
	{
		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token))
		{
			// 请求头未携带token，需要抛异常
			throw new AppException("请求参数错误");
		}

		if (!redisTemplate.hasKey(token))
		{
			// token不存在，说明token已经被其他请求删除或者是非法的token
			throw new AppException("接口重复提交");
		}

		boolean flag = redisTemplate.delete(token);
		if (flag == false)
		{
			// token删除失败，说明token已经被其他请求删除
			throw new AppException("接口重复提交");
		}
	}

}
