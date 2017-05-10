package com.thinkequip.bizfw.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkequip.bizfw.base.BizfwServiceException;
import com.thinkequip.bizfw.base.common.RedisUtils;
import com.thinkequip.bizfw.po.service.PeopleService;

public class TestQuartz {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	PeopleService peopleService;

	public void execute() {
		try {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				peopleService.getByCode("admin");

			}
			long end = System.currentTimeMillis();
			long qqq = end - start;
			logger.info("normal time consumption:" + qqq);

			start = System.currentTimeMillis();
			for (int i = 0; i < 10000; i++) {
				String b = RedisUtils.get("a");
			}
			end = System.currentTimeMillis();
			qqq = end - start;
			logger.info("redis time consumption:" + qqq);
		} catch (BizfwServiceException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
