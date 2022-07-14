package com.randke.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.randke.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * bond数据源配置
 *
 */
@Configuration
@MapperScan(basePackages = {"com.randke.mapper.test2"},
        sqlSessionFactoryRef = Test2DataSourceConfig.SESSION_FACTORY_NAME)
public class Test2DataSourceConfig extends BaseSourceConfig {
    public static final String TRANSACTION_NAME = "bond2TransactionManager";

    public static final String DATA_SOURCE_NAME = "bond2DataSource";
    public static final String DATA_SOURCE_PREFIX = "bond2.datasource";
    public static final String SESSION_FACTORY_NAME = "bond2SqlSessionFactory";
    protected static final String[] ALIAS_PACKAGES = {"com.innodealing.international.bondprimary.model.entity"};

    /**
     * 创建数据源
     *
     * @return 返回数据源
     */
    @Bean(name = DATA_SOURCE_NAME, initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = DATA_SOURCE_PREFIX)
    public DruidDataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置事务
     *
     * @return 事务
     */
    @Bean(name = TRANSACTION_NAME)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 创建SqlSessionFactory对象
     *
     * @param dataSource 数据源
     * @return SqlSessionFactory对象
     * @throws Exception 异常
     */
    @Bean(name = SESSION_FACTORY_NAME)
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        return super.getSessionFactory(dataSource, ALIAS_PACKAGES);
    }
}
