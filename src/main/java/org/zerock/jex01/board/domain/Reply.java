package org.zerock.jex01.board.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    private Long rno; //Lnog이 기본값
    private Long bno;
    private String replyer;
    private String reply;
    private LocalDateTime replyDate;
    private LocalDateTime modDate;
}
