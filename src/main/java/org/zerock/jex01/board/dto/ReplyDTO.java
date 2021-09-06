package org.zerock.jex01.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO { //VO 객체 생성
    //mybatis에서는 DTO,VO 구분없이 써도 되는데 JPA에서는 둘을 구분해서 사용함

    private Long rno; //spring JPA(mybatis랑 비슷한데 요새 많이 쓰는 것) 에서는 래퍼 타입(래퍼런스 타입)Long이 기본값.JPA는 해외에서 많이 사용.
    private Long bno;
    private String replyer;
    private String reply;

    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime replyDate;
    @JsonFormat(shape =  JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;

}
