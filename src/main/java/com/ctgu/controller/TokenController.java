package com.ctgu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctgu.common.ResultMsg;
import com.ctgu.service.TokenService;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: TokenController
 * @Description:
 * @author lh2
 * @date 2020年6月16日 下午7:09:28
 */
@RestController
@RequestMapping("/token")
public class TokenController
{
	@Autowired
	private TokenService tokenService;

	@GetMapping
	@ApiOperation(value = "获取token接口  ", notes = " ")
	public ResultMsg token()
	{
		String token = tokenService.createToken();
		return ResultMsg.success(token);
	}

}