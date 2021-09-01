package org.zerock.jex01.common.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
//코드문제 -> 500 / 서버(URL)쪽 문제 -> 404 : web.xml(=webConfig)확인
    @ExceptionHandler(Exception.class) //괄호안에 어떤 종류의 예외가 발생하면 이 메서드를 실행할거야
    //직접 에러처리를 해주지않아도 알아서 처리해준다. -> 에러가 발생하면 exception 클래스로 가는 것
    public String exceptAll(Exception ex, Model model){

        log.error(ex.getClass().getName());
        log.error(ex.getMessage());

        //모델에 exception을 추가
        model.addAttribute("exception",ex); // exception이라는 이름으로 ex를 모델에 담는다

        return "error_page";
    }

    //여기에서 에러로 안걸리면 상위로 빠짐 (exceptAll())
    //ArithmeticException
    @ExceptionHandler(ArithmeticException.class) //괄호안에 어떤 종류의 예외가 발생하면 이 메서드를 실행할거야
    //직접 에러처리를 해주지않아도 알아서 처리해준다. -> 에러가 발생하면 exception 클래스로 가는 것
    public String exceptArithmetic(ArithmeticException ex, Model model){

        log.info("exceptarithmetic");
        log.error(ex.getClass().getName());
        log.error(ex.getMessage());

        //모델에 exception을 추가
        model.addAttribute("exception",ex); // exception이라는 이름으로 ex를 모델에 담는다

        return "error_page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex){
        return "custom404";// 404에러 페이지 만들어 줌
    }
}
