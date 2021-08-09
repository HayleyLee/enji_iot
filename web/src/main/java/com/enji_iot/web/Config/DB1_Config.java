package com.enji_iot.web.Config;

import com.enji_iot.util.Util.PropertiesUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.enji_iot.data.DAO","com.enji_iot.develop.DAO","com.enji_iot.mqtt.DAO","com.enji_iot.cache.DAO"}, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1_Config {
    @Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
    @Bean("db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1") //读取application.yml中的配置参数映射成为一个对象
    public static DataSource getDb1DataSource(DataSourceProperties p){
//        return DataSourceBuilder.create().build();
        Properties properties = PropertiesUtil.getProperties("application.properties");

//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(properties.getProperty("spring.datasource.db1.jdbc-url"));
//        hikariConfig.setUsername(properties.getProperty("spring.datasource.db1.username"));
//        hikariConfig.setPassword(properties.getProperty("spring.datasource.db1.password"));
//        hikariConfig.setDriverClassName(properties.getProperty("spring.datasource.db1.driver-class-name"));
////        return new HikariDataSource(hikariConfig);
//
//        return DataSourceBuilder.create()
//                .type(new HikariDataSource(hikariConfig).getClass())
//                .build();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(properties.getProperty("spring.datasource.db1.jdbc-url"));
        dataSource.setUsername(properties.getProperty("spring.datasource.db1.username"));
        dataSource.setPassword(properties.getProperty("spring.datasource.db1.password"));
        dataSource.setDriverClassName(properties.getProperty("spring.datasource.db1.driver-class-name"));
        return dataSource;
    }

    @Primary
    @Bean("db1SqlSessionFactory")
    public static SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/db1/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com.enji_iot.*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean("db1SqlSessionTemplate")
    public static SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
