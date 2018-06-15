package org.ys.soft.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ys.soft.common.lock.distributed.RedisLock;
import org.ys.soft.common.service.RedisCommonService;
import org.ys.soft.framework.base.utils.test.ConcurrencyTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisCommonService redisCommonService;

	@Autowired
	private RedisLock redisLock;

	@Test
	public void testHello() {
		logger.info("This is spring boot test.");
	}

	public void testRedisLock() {
		redisLock.lock("VISIT", new org.ys.soft.common.lock.Callback() {
			@Override
			public <T> T execute() {
				logger.info("xxxxxxxxxxxxxx visit count = " + redisCommonService.getVisitCount(60, "www.phoneerp.com"));
				return null;
			}
		});
	}

	public void testVisitCount() {
		new ConcurrencyTestUtils(new org.ys.soft.framework.base.utils.test.concurrency.Callback() {
			@Override
			public void execute() {
				testRedisLock();
			}
		}).doTest(100);
	}
}
