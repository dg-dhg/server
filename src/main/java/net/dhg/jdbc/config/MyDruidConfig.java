package net.dhg.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
/*
* 这个类是servlet的一个配置类
*
* */
@Configuration
public class MyDruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /*
     * 这种方式相当于web.xml
     * servlet内置的容器没有xml(难道说是外部配置)
     *德鲁伊后台监控,这个监控面板(功能是druid提供的,只需要设置一些账号或路径信息)
     *
     * springboot内置了servlet容器,所以使用servlet
     * */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean= new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台登陆
        HashMap<String,String> initParameters=new HashMap<>();

        //设置一些配置...
        //账号密码
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        //允许访问
        initParameters.put("allow","");
        initParameters.put("laodengtou","192.168.0.1");
        //这个方法是ServletRegistrationBean父类的一个方法
        bean.setInitParameters(initParameters);
        return bean;
    }

    //过滤器注册器
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());
        HashMap<String,String> initParameters = new HashMap<>();

        /*这里是配置*/
        initParameters.put("exclusions","*.js,*.css,/druid/**");//过滤器不统计这些东西~



        bean.setInitParameters(initParameters);
        return bean;
    }

}
