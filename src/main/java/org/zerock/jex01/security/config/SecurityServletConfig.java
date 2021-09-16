package org.zerock.jex01.security.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@ComponentScan(basePackages = {"org.zerock.jex01.security.controller"})
@EnableGlobalMethodSecurity(prePostEnabled = true)  //안전하게 어노테이션을 걸어준다
public class SecurityServletConfig {
}
