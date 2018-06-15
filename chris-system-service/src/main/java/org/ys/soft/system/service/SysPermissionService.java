package org.ys.soft.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ys.soft.framework.service.BaseDao;
import org.ys.soft.framework.service.BaseService;
import org.ys.soft.system.dao.SysPermissionDao;
import org.ys.soft.system.model.SysPermission;

/**
 * [权限服务类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service
public class SysPermissionService extends BaseService<SysPermission> {

	private Logger logger = LoggerFactory.getLogger(SysPermissionService.class);

	@Autowired
	private SysPermissionDao sysPermissionDao;

	@Override
	protected BaseDao<SysPermission> getBaseDao() {
		return sysPermissionDao;
	}

	/**
	 * [保存初始化权限数据]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	@Transactional(rollbackForClassName = { "RuntimeException", "Exception" })
	public void saveInitPermissionData(List<SysPermission> permissionList) {
		SysPermission param = new SysPermission();
		int count = 0;
		// 查出数据库中存在的所有权限
		List<SysPermission> deleteDataList = sysPermissionDao.retrieveList(param);
		// 循环遍历待保存的权限数据
		for (SysPermission permission : permissionList) {
			param.setCode(permission.getCode());
			count = sysPermissionDao.getCount(param);
			if (count == 0) {
				// 权限在数据库中不存在
				logger.info(permission + " not exist in database, will add it.");
				sysPermissionDao.create(permission);
			} else {
				// 该权限在数据库中已存在
				logger.info(permission + " has exist in database, will ignore it.");
				deleteDataList.remove(permission);
			}
		}
		// 循环完成后,删除数据库中无效的权限
		for (SysPermission permission : deleteDataList) {
			// 该权限在数据库中存在,但是已被废弃,删除
			logger.info(permission + " has exist in database, but it is ineffective now, will delete it.");
			sysPermissionDao.deleteByPk(permission);
		}

		logger.info("Save init permission data has been completed.");
	}

	/**
	 * [获取用户和权限的绑定记录个数]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public int getUserPermissionCount(Long userId, String permissionCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("permissionCode", permissionCode);
		return sysPermissionDao.getUserPermissionCount(paramMap);
	}

	/**
	 * [获取用户可使用的菜单编码集合]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<String> getAccessMenuCodeList(Long userId) {
		return sysPermissionDao.getAccessMenuCodeList(userId);
	}

	/**
	 * [获取可使用菜单下所有权限集合(若角色拥有该权限,checked字段为true)]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public List<SysPermission> getAccessMenuPermissionList(Long roleId) {
		return sysPermissionDao.getAccessMenuPermissionList(roleId);
	}

}
