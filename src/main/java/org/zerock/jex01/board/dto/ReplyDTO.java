package org.zerock.jex01.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDTO { //VO 객체 생성
    //mybatis에서는 DTO,VO 구분없이 써도 되는데 JPA에서는 둘을 구분해서 사용함

    private Long rno; //spring JPA(mybatis랑 비슷한데 요새 많이 쓰는 것) 에서는 래퍼 타입(래퍼런스 타입)Long이 기본값.JPA는 해외에서 많이 사용.
    private Long bno;
    private String replyer;
    private String reply;
    private LocalDateTime replyDate;
    private LocalDateTime modDate;

}
