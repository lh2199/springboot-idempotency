package com.ctgu.common;

/**
 * @ClassName: AppException
 * @Description: 自定义异常
 * @author lh2
 * @date 2020年6月16日 下午6:09:31
 */
public class AppException extends RuntimeException
{
	// public int OK = 200;
	// public int CREATED = 201;
	// public int NOT_FOUND = 204;
	// public int HAS_EXIST = 205;
	// public int PARAM_ERROR = 400;
	// public int NOT_AUTHENTICATION = 401;
	// public int NO_PERMISSION = 403;
	// public int INTERNAL_SERVER_ERROR = 500;

	private int code = 400;

	public int getCode()
	{
		return this.code;
	}

	public AppException(String message)
	{
		super(message);
	}

	public AppException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AppException(String message, Throwable cause, int code)
	{
		super(message, cause);
		this.code = code;
	}

	public AppException(String message, int code)
	{
		super(message);
		this.code = code;
	}
}
