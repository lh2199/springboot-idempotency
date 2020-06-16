package com.ctgu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: ApiIdempotent
 * @Description: 接口幂等性注解
 * @author lh2
 * @date 2020年6月16日 下午5:28:42
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent
{

}
