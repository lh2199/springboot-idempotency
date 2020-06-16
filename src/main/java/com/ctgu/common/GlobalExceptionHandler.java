package com.ctgu.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @author lh2
 * @date 2020年6月16日 下午6:07:33
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{

	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ResultMsg exception(BindException e)
	{
		log.error(e.getMessage(), e);
		return ResultMsg.fail(500, convertMessage(e.getBindingResult()));

	}

	/**
	 * 服务端错误
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResultMsg runtimeException(RuntimeException e)
	{
		log.error(e.getMessage(), e);
		return ResultMsg.fail(500, e.getMessage());

	}

	/**
	 * 参数校验
	 *
	 * @param e
	 * @return
	 * @Valid
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public ResultMsg methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e)
	{
		log.error(e.getMessage(), e);

		return ResultMsg.fail(400, convertMessage(e.getBindingResult()));

	}

	/**
	 * 全局业务异常捕获
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	@ResponseBody
	public ResultMsg appExceptionHandler(AppException e)
	{
		log.error(e.getMessage(), e);
		return ResultMsg.fail(400, e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultMsg appExceptionHandler(Exception e)
	{
		log.error(e.getMessage(), e);

		return ResultMsg.fail(400, e.getMessage());
	}

	private String convertMessage(BindingResult result)
	{
		if (result.hasErrors())
		{
			final List<String> collect = result.getAllErrors()
					.stream()
					.map(x -> x.getDefaultMessage())
					.collect(Collectors.toList());

			return StringUtils.collectionToCommaDelimitedString(collect);
		}
		return null;
	}
}
