package org.ys.soft.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * [{@code @SpringBootApplication}是SpringBoot项目的核心注解,主要用来开启自动配置. ]
 * 
 * @author Chris li[黎超]
 * @version [版本, 2017-04-12]
 * @see
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.ys.soft")
@MapperScan("org.ys.soft.common.dao")
public class ProviderApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类,用于独立tomcat运行的入口
		return builder.sources(ProviderApplication.class);
	}

	/**
	 * [SpringBoot启动的方法]
	 * 
	 * @author Chris li[黎超]
	 * @version [版本, 2017-04-12]
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
}
