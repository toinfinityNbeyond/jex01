package org.zerock.jex01.board.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.board.service.TimeService;
import org.zerock.jex01.common.config.RootConfig;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {BoardRootConfig.class, RootConfig.class})// 두 개다 로딩해줘야 에러가 안난다
public class TimeServiceTests {

    @Autowired
    TimeService timeService;

    @Test
    public void testAdd() {

        String str =  "Go down\n" +
                "Soft sound\n" +
                "Midnight\n" +
                "Car lights\n" +
                "Playing with the air\n" +
                "Breathing in your hair\n" +
                "Go down\n" +
                "Soft sound\n";

        timeService.addString(str);


    }

}
