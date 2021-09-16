package org.zerock.jex01.security.config;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.jex01.security.handler.CustomAccessDeniedHandler;
import org.zerock.jex01.security.handler.CustomAuthenticationEntryPoint;
import org.zerock.jex01.security.handler.CustomLoginSuccessHandler;
import org.zerock.jex01.security.mapper.MemberMapper;
import org.zerock.jex01.security.service.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Log4j2
@MapperScan(basePackages = "org.zerock.jex01.security.mapper")
@ComponentScan(basePackages = "org.zerock.jex01.security.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MemberMapper memberMapper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(); // 이 방법을 통해서 패스워드를 어렵게 만들어줄 것이다~~
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//       http.authorizeRequests() // 인가 받을 사용자만 사용할 거다
//               .antMatchers("/sample/doAll").permitAll()
//               .antMatchers("/sample/doMember").access("hasRole('ROLE_MEMBER')")
//               .antMatchers("/sample/doAdmin").access("hasRole('ROLE_ADMIN')");

            //http.formLogin()  <- 로그인 페이지를 보여주는 역할(자동으로 보여준다)
            http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login");

            //http.logout().invalidateHttpSession(true);// 디폴드 값이라 주지 않아도 작동
            http.csrf().disable(); //시큐리티 설정에 csrf를 사용하지 않겠다.

            http.rememberMe().tokenRepository(persistentTokenRepository())
                    .key("zerock").tokenValiditySeconds(60*60*24*30);

            http.exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint());

                    //.accessDeniedHandler(customAccessDeniedHandler());

    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
        }


    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(){  //빈으로 등록
        return new CustomAuthenticationEntryPoint();
    }


    @Bean //빈으로 등록
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailsService); // 얘를 통해서 로그인 프로세스를 진행한다~

        // 로그인 할 떄 사용자만 member1인지 알 수 있다
        //inMemoryAuthentication()를 호출하는 것으로 인메모리 사용자 저장소가 활성화 및 설정이 가능하고 선택적으로 인메모리 사용자 저장소에 값을 채울 수 있다.
        //auth.inMemoryAuthentication().withUser("member1").password("$2a$10$S9IWhMaX06oWxWk4BlKL5ObyCarNkSSNpwC7x57aafbatyNl3W8Nm").roles("MEMBER"); // roles 는 권한
        //auth.inMemoryAuthentication().withUser("admin1").password("$2a$10$S9IWhMaX06oWxWk4BlKL5ObyCarNkSSNpwC7x57aafbatyNl3W8Nm").roles("MEMBER", "ADMIN");
    }

//    @Bean
//    public CustomUserDetailsService customUserDetailsService() {
//        return new CustomUserDetailsService(memberMapper);
//    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;

    }

}
