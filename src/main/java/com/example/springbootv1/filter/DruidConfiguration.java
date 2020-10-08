package com.example.springbootv1.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : pp
 * @Date : 2020/10/8 17:09
 * 开启监控功能 这里使用代码注册servlet和filter的方式处理
 */
@Configuration
public class DruidConfiguration {

    // 1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        //ServletRegistrationBean提供类的注册
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(),"/druid/*");
        //添加初始化参数
        //白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单(存在共同时 , deny优先于allow)
        //如果满足deny的话 , 提示:sorry , you are not permitted to view this page
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看账号和密码的信息
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    // 2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //添加要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png ,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
