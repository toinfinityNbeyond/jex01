package org.zerock.jex01.board.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//aspect라는 어노테이션이 적용하려고 하려면
@Aspect
@Log4j2
@Component
public class TimeLogAspect {


    {
        log.info("TimeLogAspect............");
        log.info("TimeLogAspect............");
        log.info("TimeLogAspect............");
        log.info("TimeLogAspect............");
        log.info("TimeLogAspect............");
        log.info("TimeLogAspect............");


    } // 객체가 생성되자마자 실행이 되는 블럭.  디폴트 블럭. 로그를 찍

    @Before("execution(* org.zerock.jex01.board.service.*.*(..))") //포인트 컷 -> 이 기능을 누구랑 합칠건지 . *접근 제한자
    public void logBefore(JoinPoint joinPoint) {
        log.info("logBefore.........");

        log.info(joinPoint.getTarget()); //실제 객체
        log.info(Arrays.toString(joinPoint.getArgs()));

        log.info("logBefore..........END");

    }

    @AfterReturning("execution(* org.zerock.jex01.board.service.*.*(..))") //포인트 컷 -> 이 기능을 누구랑 합칠건지 . *접근 제한자
    public void logAfter() {
        log.info("logAfter...........");

    }
}
