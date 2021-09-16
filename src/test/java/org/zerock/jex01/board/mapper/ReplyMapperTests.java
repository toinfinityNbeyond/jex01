package org.zerock.jex01.board.mapper;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.board.domain.Reply;
import org.zerock.jex01.common.config.RootConfig;

import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class) // 해당코드가 스프링을 실행한다고 알려줌
@ContextConfiguration( classes = {BoardRootConfig.class, RootConfig.class}) //지정 된 클래스나 문자열을 이용해서 필요한 객체를 등록 (Spring의 Bean으로 등록된다.)
public class ReplyMapperTests {

    @Autowired(required = false) //스프링이 로딩할 때 Autowired 이 가능한지 체크하지 않는다.
    // 의존객체를 주입받지 못하면 에러가 난다 (false 로 해놨기 때문에 로딩 할 때 체크하지 않는다.기본값은 true)-> 객체에 주입 받지 못하더라도 빈을 생성.

    ReplyMapper replyMapper;  // 스프링의 configration 작동 ㄴㄴ

     @Test
    public void insertDummies() {

         long[] arr = {230L, 229L, 228L, 227L, 226L};  //bno

         IntStream.rangeClosed(1,100).forEach(num -> {   //IntStream는 루프로 생각

             long bno = arr[  ((int)(Math.random() * 1000)) % 5 ]; // 0 ~ 0.999 math.ramdom

             Reply reply = Reply.builder()
                                 .bno(bno)
                                 .reply("댓글...." + num)
                                 .replyer("user" + (num % 10))
                                 .build();


             replyMapper.insert(reply);

         });

     }

        @Test
        public void testList() {
            long bno = 230L;

            replyMapper.getListWithBoard(bno).forEach(reply -> log.info(reply));
        }


}
