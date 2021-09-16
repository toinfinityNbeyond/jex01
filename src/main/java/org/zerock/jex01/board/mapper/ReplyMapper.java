package org.zerock.jex01.board.mapper;

import org.zerock.jex01.board.domain.Reply;

import java.util.List;

public interface ReplyMapper {  //보드 매퍼와 비슷한데 타입이 다르다


    int insert(Reply reply); //몇 개가 변경됬는지 알려준다

    List<Reply> getListWithBoard(Long bno);

    int delete(long rno);

    int update(Reply reply);


}
