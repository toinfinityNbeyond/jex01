package org.zerock.jex01.common.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.board.config.BoardServletConfig;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

@Log4j2
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {//루트설정

        log.info("1-----------------------");
        log.info("1-----------------------");

        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//서블릿설정

        log.info("2-----------------------");
        log.info("2-----------------------");//정상적으로 설정이 완료되면 로그가 찍히는 것.

        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //FrontController를 통해 다른 Controller로 들어갈 수 있게 하는 메서드 : 아직 경로 미완성
    }

    @Override //한글처리
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
        utf8Filter.setEncoding("UTF-8");
        utf8Filter.setForceEncoding(true);

        return new Filter[]{utf8Filter};
    }//POST방식으로만 가능 GET방식은 아직 X

    //p.161 예제확인
    //404에러 (찾을 수 없는 URL을 입력했을 때 custom404.jsp로 가도록 해줌)
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
    }
}
