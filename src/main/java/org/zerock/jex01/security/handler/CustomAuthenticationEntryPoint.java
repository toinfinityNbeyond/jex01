package org.zerock.jex01.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    // AuthenticationException 인증에러가 파라미터로 들어간다
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {

        log.error("------------------CustomAuthenticationEntryPoint-----------------");
        log.error("------------------CustomAuthenticationEntryPoint-----------------");
        log.error(authException);
        log.error("------------------CustomAuthenticationEntryPoint-----------------");
        log.error("------------------CustomAuthenticationEntryPoint-----------------");

        if(req.getContentType() != null && req.getContentType().contains("json")){
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(403);
            res.getWriter().write("{\'msg\':\'REST API ERROR\'}"); // 댓글을 추가하면 이 메시지가 날아가게 된다.
        }else {

            res.sendRedirect("/customLogin?erzzzzzzs");
        }



    }
}