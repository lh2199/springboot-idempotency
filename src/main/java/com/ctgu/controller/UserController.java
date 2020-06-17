package com.ctgu.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctgu.annotation.ApiIdempotent;
import com.ctgu.common.ResultCode;
import com.ctgu.common.ResultMsg;
import com.ctgu.entity.User;
import com.ctgu.service.IUserService;
import com.ctgu.vo.UserInputVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: UserController
 * @Description: 测试接口
 * @author lh2
 * @date 2020年6月2日 下午3:05:19
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "用户相关操作")
public class UserController
{
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "userService")
	private IUserService userService;

	// 在请求接口时，要带上token
	@ApiIdempotent
	@GetMapping("/test-idempotent")
	@ApiOperation(value = "测试接口  ", notes = " ")
	public ResultMsg testIdempotent(@RequestHeader("token") String token)
	{
		User user = new User();
		user.setPassword("123");
		user.setUserName("张三");
		user.setGender(0);
		boolean flag = userService.addUser(user);
		if (flag)
		{
			return ResultMsg.success(" idempotent test successfully");
		}
		else
		{
			return ResultMsg.fail(ResultCode.INTERNAL_SERVER_ERROR);
		}
	}

}
