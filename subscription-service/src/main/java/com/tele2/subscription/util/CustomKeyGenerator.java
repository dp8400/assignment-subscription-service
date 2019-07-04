package com.tele2.subscription.util;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class CustomKeyGenerator implements KeyGenerator{

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return target.getClass().getSimpleName() + "_"
		          + method.getName() + "_"
		          + System.currentTimeMillis();
	}

}
