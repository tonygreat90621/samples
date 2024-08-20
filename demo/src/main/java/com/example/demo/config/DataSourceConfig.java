package com.example.demo.config;

import com.example.demo.config.db.MyDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public Map<String, HikariConfig> getConfig(){
        return new HashMap<>();
    }

    @Primary
    @Bean("dataSource")
    public DataSource getDataSource(){
        Map<String,HikariConfig> config = getConfig();
        Map<Object,Object> configMap = new HashMap<>();
        config.forEach((k,v)->{
            HikariDataSource hikariDataSource = new HikariDataSource(v);
            configMap.put(k,hikariDataSource);
        });
        MyDataSource dataSource = new MyDataSource();
        dataSource.setTargetDataSources(configMap);
        dataSource.setDefaultTargetDataSource(configMap.get("ds1"));
        return dataSource;
    }
}
