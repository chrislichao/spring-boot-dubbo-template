package org.ys.soft.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ys.soft.framework.base.Where;
import org.ys.soft.framework.base.WhereFactory;
import org.ys.soft.system.model.SysPermission;
import org.ys.soft.system.service.SysPermissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysPermissionService sysPermissionService;

	@Test
	public void testHello() {
		logger.info("This is spring boot test.");
	}

	@Test
	public void testService() throws Exception {
		SysPermission query = new SysPermission();
		query.setId(8098908989L);
		query.setRemark("系统初始化生成");
		Where where = WhereFactory.createInstance(SysPermission.class);
		where.field("id").equal(8098908989L);
		logger.info("主键更新:" + sysPermissionService.updateByPk(query));
		logger.info("条件更新:" + sysPermissionService.updateBatch(query, where));
		logger.info("主键删除:" + sysPermissionService.deleteByPk(query));
		logger.info("条件删除:" + sysPermissionService.deleteBatch(query));
	}
}
