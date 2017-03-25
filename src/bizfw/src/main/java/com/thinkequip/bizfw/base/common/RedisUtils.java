package com.thinkequip.bizfw.base.common;

import redis.clients.jedis.Jedis;

public class RedisUtils {

	private static Jedis jedis = new Jedis("127.0.0.1", 6379);

	public static void set(String key, String value) {
		jedis.set(key, value);
	}

	public static String get(String key) {
		return jedis.get(key);
	}
}
