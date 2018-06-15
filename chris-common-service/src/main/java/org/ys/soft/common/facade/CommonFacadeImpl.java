package org.ys.soft.common.facade;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.ys.soft.common.model.LogMonitor;
import org.ys.soft.common.service.RedisCommonService;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * [公共dubbo服务接口实现]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Service(version = "1.0.0")
public class CommonFacadeImpl implements CommonFacade {

	@Autowired
	private RedisCommonService redisCommonSerive;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	@Value("${log.queue}")
	private String logQueueName;

	@Override
	public long getVisitCount(long timeout, String visitUrl) {
		return redisCommonSerive.getVisitCount(timeout, visitUrl);
	}

	@Override
	public void saveLog(LogMonitor log) {
		Destination destination = new ActiveMQQueue(logQueueName);
		jmsTemplate.convertAndSend(destination, log);
	}
}
