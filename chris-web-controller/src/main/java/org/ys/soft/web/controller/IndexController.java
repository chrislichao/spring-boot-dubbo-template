package org.ys.soft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ys.soft.framework.base.annotation.Monitor;
import org.ys.soft.framework.base.annotation.RateLimit;

/**
 * [主页的控制类]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@Controller
public class IndexController {

	@RateLimit(timeout = 10, count = 10)
	@Monitor(description = "访问系统主页")
	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return "OK";
	}
}
