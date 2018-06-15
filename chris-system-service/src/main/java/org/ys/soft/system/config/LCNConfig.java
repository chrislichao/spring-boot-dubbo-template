package org.ys.soft.system.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codingapi.tx.config.service.TxManagerTxUrlService;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages={"com.codingapi.tx.","org.ys.soft.system."})
public class LCNConfig implements TxManagerTxUrlService {

	@Value("${tx.manager.url}")
	private String txManagerUril;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public String getTxUrl() {
		return txManagerUril;
	}

    @Bean("transactionManager")
    public PlatformTransactionManager txManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}
