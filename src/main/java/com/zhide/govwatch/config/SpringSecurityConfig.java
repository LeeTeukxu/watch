package com.zhide.govwatch.config;

import com.zhide.govwatch.define.ItbLoginUserService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbClient;
import com.zhide.govwatch.model.v_LoginUser;
import com.zhide.govwatch.repository.tbClientRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * @ClassName: SpringSecurityConfig
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年04月02日 12:48
 **/
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
    @Autowired
    ItbLoginUserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            tbClientRepository clientRep;
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                v_LoginUser user =userService.findByCode(s);
                if (user == null) {
                    Optional<tbClient> findClients=clientRep.findFirstByCreditCode(s);
                    if(findClients.isPresent()==false)throw new UsernameNotFoundException(s + "在系统中不存在!");
                    else {
                        tbClient findClient=findClients.get();
                        String password=findClient.getPassword();
                        if(StringUtils.isEmpty(password)){
                            password=passwordEncoder().encode("123456");
                            findClient.setPassword(password);
                        }
                        return new LoginUserInfo(findClient);
                    }
                } else return new LoginUserInfo(user);
            }
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable();
        http
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/index")
                .loginProcessingUrl("/login")
                .successHandler(getSuccess())
                .failureHandler((request,response,exception)->{
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out=response.getWriter();
                    out.write("<html>");
                    out.write(" <link rel=\"stylesheet\" href=\"/js/layui/css/layui.css\" media=\"all\"/>");
                    out.write("<script type=\"text/javascript\" src=\"/js/layui/layui.js\"></script>");
                    out.write("<script type='text/javascript'>" );
                    out.write("layui.use(['form'],function(){" +
                            "       var layer=layui.layer;" +
                            "       layer.open({" +
                                        "title:'登录错误',"+
                                        "offset:'400px',"+
                                        "area:['500px','200px'],"+
                            "           content:'用户名称或密码错误,请重新输入用户或登录密码进行登录操作!'," +
                            "           yes:function(index){" +
                            "                window.location.href='/login.html';" +
                                        "}"+
                            "       });" +
                            "});" );
                    out.write("</script>");
                    out.write("</html>");
                    out.close();
                })
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    request.getSession().removeAttribute("LoginUser");
                    if (authentication != null) logger.info(authentication.getName() + "退出了系统!");
                })
                .logoutSuccessUrl("/login.html")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/login", "/index", "/", "/PostWeb/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/font/**","/img/**","/js/**", "/css/**", "/res/**","/appImages/**","/WebAPI/**");

    }

    @Bean
    public AuthenticationSuccessHandler getSuccess() {
        LoginSuccessHandler handler = new LoginSuccessHandler();
        handler.setDefaultTargetUrl("/index");
        return handler;
    }
}
