package com.saber.lrdao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据源配置类(JDBCTemplate)
 */
@Configuration
@ComponentScan(basePackages = "com.saber")
public class SourceConfig
{
    /**
     * 配置数据源的账号密码
     */
    private static final String url="jdbc:mysql://localhost:3306/exercise?serverTimeZone=Asia/Shanghai";
    private static final String userName="root";
    private static final String password="saber520";

    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        jdbcTemplate.setDataSource(ds);
        return jdbcTemplate;
    }

}
