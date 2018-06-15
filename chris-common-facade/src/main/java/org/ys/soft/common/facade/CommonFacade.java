package org.ys.soft.common.facade;

import org.ys.soft.common.model.LogMonitor;

/**
 * [公共dubbo服务接口]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
public interface CommonFacade {
	/**
	 * [获取本时间周期{timeout}秒内,{visitUrl}链接的访问次数]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public long getVisitCount(long timeout, String visitUrl);

	/**
	 * [保存日志对象]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public void saveLog(LogMonitor log);
}
