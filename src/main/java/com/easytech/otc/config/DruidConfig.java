package com.easytech.otc.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DruidConfig {

    @Value("${jdbc.druid.url}")
    private String  url;
    @Value("${jdbc.druid.username}")
    private String  username;
    @Value("${jdbc.druid.password}")
    private String  password;
    @Value("${jdbc.druid.driverClassName}")
    private String  driverClassName;

    @Value("${jdbc.druid.filters}")
    private String  filters;

    @Value("${jdbc.druid.maxActive}")
    private int     maxActive;
    @Value("${jdbc.druid.initialSize}")
    private int     initialSize;
    @Value("${jdbc.druid.maxWait}")
    private int     maxWait;
    @Value("${jdbc.druid.minIdle}")
    private int     minIdle;

    @Value("${jdbc.druid.timeBetweenEvictionRunsMillis}")
    private long    timeBetweenEvictionRunsMillis;
    @Value("${jdbc.druid.minEvictableIdleTimeMillis}")
    private long    minEvictableIdleTimeMillis;
    @Value("${jdbc.druid.validationQuery}")
    private String  validationQuery;

    @Value("${jdbc.druid.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${jdbc.druid.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${jdbc.druid.testOnReturn}")
    private boolean testOnReturn;

    @Value("${jdbc.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${jdbc.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int     maxPoolPreparedStatementPerConnectionSize;
    @Value("${jdbc.druid.connectionProperties}")
    private String  connectionProperties;

    @Value("${jdbc.druid.stat.loginUsername}")
    private String  loginUsername;
    @Value("${jdbc.druid.stat.loginPassword}")
    private String  loginPassword;

    @Bean
    @Primary //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        datasource.setConnectionProperties(connectionProperties);

        datasource.setProxyFilters(Lists.newArrayList(statFilter(), wallFilter()));
        return datasource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistration = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单
        servletRegistration.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistration.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistration.addInitParameter("loginUsername", loginUsername);
        servletRegistration.addInitParameter("loginPassword", loginPassword);
        //是否能够重置数据.
        servletRegistration.addInitParameter("resetEnable", "false");
        return servletRegistration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }
}