package net.dhg.jdbc.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//开启web 安全模式，被security托管
@EnableWebSecurity
public class NewASpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*这个方法被重写很多次，看来是个比较重要的功能，链式编程*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1拦截请求（匹配身份和请求地址）授权？
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/loginUser/level1/**").hasRole("vip1")
                .antMatchers("/loginUser/level2/**").hasRole("vip2")
                .antMatchers("/loginUser/level3/**").hasRole("vip3");
        //2登录页
        http.formLogin().loginPage("/loginUser/login").loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("pwd");

        //3.对注销进行一些操作
        //.logout().deleteCookies(&quot;remove&quot;).invalidateHttpSession(false)//一般不清空session和cookie
        //.logoutUrl(&quot;/custom-logout&quot;).logoutSuccessUrl(&quot;/logout-success&quot;);
        http.logout().logoutSuccessUrl("/loginUser/").permitAll();

        //4.关闭跨站请求伪造攻击防御,登录登出根据get请求进行交互,而这涉及到Session和cookie的操作,
        //这在spring security 看来是不安全的
        http.csrf().disable();

        //5.勿忘我 默认最长记忆时间两周
        http.rememberMe().rememberMeParameter("rememberMe");


    }

    /*认证。。。在2.1.x时可用，在2.2.x后需加密密码
     * 在ss5（SpringSecurity中有许多的加密的方法5）'
     * 这里推荐使用BCryptEncoder
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*这是在内存中设置账号和密码，一般是在数据库中查找进行校验*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("dhg").password(encoder.encode("123456")).roles("vip1", "vip2")
                .and().withUser("root").password(encoder.encode("123456")).roles("vip1", "vip2", "vip3")
                .and().withUser("guest").password(encoder.encode("111222")).roles("vip1");
    }
}
