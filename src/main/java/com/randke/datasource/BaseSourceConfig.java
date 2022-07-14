package com.randke.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * 数据源配置
 *
 * @author yan
 * @time 2021-12-14
 */
class BaseSourceConfig {
    SqlSessionFactory getSessionFactory(DataSource dataSource, String[] aliasPackages) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(String.join(",", aliasPackages));
        SqlSessionFactory factory = sessionFactory.getObject();
        if (Objects.nonNull(factory)) {
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }
        return factory;
    }
}
