package com.saber.lrdao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置类(JDBCTemplate)
 */
@Configuration
@ComponentScan(basePackages = "com.saber.lrdao.dao")
public class SourceConfig
{
    /**
     * 配置数据源的账号密码
     */
    private static final String url="jdbc:mysql://localhost:3306/exercise?serverTimezone=Asia/Shanghai";
    private static final String userName="root";
    private static final String password="saber520";

    @Bean
    DataSource dataSource()
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        return new JdbcTemplate(dataSource());
    }

    @Bean//注册数据库事务管理器具体类
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource)
    {
        return new DataSourceTransactionManager(dataSource);
    }

}
