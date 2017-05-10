package com.thinkequip.bizfw.base;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.auth.model.Role;
import com.thinkequip.bizfw.auth.service.RoleService;

public class RedisTest extends BaseTest {

	@Autowired
	private RoleService roleService;

	@Test
	public void testSet() throws Exception{
		Role role = new Role("zzz", "redisRole", "");
		String id = roleService.saveWithCache(role);
		
		Role roleRedis = roleService.queryByIdWithCache(id);
		System.out.println(roleRedis);
	}
}
