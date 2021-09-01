package org.zerock.jex01.board.mapper;

import org.zerock.jex01.board.domain.Board;
import org.zerock.jex01.board.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {

    //@insert로 만들지 말기 -> xml로 빠짐
    void insert(Board board);

    List<Board> getList(); //첫버전은 페이징처리없어서 파라미터 없음

    Board select(Long bno);

    int delete(Long bno); // 몇 건이 삭제되었다 이런식으로 출력하기 위해
    // bno값을 넣어서 실행하면 최종적으로 나온 값의 형식

    int update(Board board);


}
