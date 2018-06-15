package org.ys.soft.web.service;

import org.springframework.stereotype.Component;
import org.ys.soft.common.facade.CommonFacade;
import org.ys.soft.common.model.LogMonitor;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * [系统服务集合]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Component
public class CommonService {

	@Reference(version = "1.0.0")
	private CommonFacade commonFacade;

	/**
	 * [获取本时间周期{timeout}秒内,{visitUrl}链接的访问次数]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public long getVisitCount(long timeout, String visitUrl) {
		return commonFacade.getVisitCount(timeout, visitUrl);
	}

	/**
	 * [保存系统方法监控日志]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void saveLog(LogMonitor log) {
		commonFacade.saveLog(log);
	}
}
