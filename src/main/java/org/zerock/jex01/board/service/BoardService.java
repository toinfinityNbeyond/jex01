package org.zerock.jex01.board.service;

import org.zerock.jex01.board.dto.BoardDTO;

import java.util.List;

//등록하는 기능을 추가 -> Controller는 화면에서 입력받는 것을 처리 : DTO를 처리하려고 함. Mapper에는 VO여서 DTO를 VO로 바꿔주는 작업이 필요함
public interface BoardService {

    Long register(BoardDTO boardDTO);

    //서비스계층은 항상 dto로 주고받음 : 파라미터를 받을 때도 리턴값도 dto -> requestDTO와 responseDTO를 나눌 수는 있음
    //영속계층을 건들지않고 dto로 값을 처리하려고.
    List<BoardDTO> getDTOList();

    BoardDTO read(Long bno);

    Boolean remove(Long bno);

    Boolean modify(BoardDTO boardDTO);


}
