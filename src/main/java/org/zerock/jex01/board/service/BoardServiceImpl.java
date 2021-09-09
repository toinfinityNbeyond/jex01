package org.zerock.jex01.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.jex01.board.domain.Board;
import org.zerock.jex01.board.dto.BoardDTO;
import org.zerock.jex01.board.mapper.BoardMapper;
import org.zerock.jex01.common.dto.PageResponseDTO;
import org.zerock.jex01.common.dto.PageRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper; // 자동으로 주입해줌

    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = boardDTO.getDomain(); //DTO를 VO로 변경

        boardMapper.insert(board);

        Long bno = board.getBno();

        board.getAttachList().forEach(attach -> {
            attach.setBno(bno);
            boardMapper.insertAttach(attach);
        });

        return board.getBno();
    }

    @Override
    public PageResponseDTO <BoardDTO> getDTOList(PageRequestDTO pageRequestDTO) {

        //boardMapper에서 가지고온 List를 한 번에 처리 (entity를 DTO로 바꾸는 기능이 필요한 것 -> function도 이용가능함)
       List<BoardDTO> dtoList = boardMapper.getList(pageRequestDTO).stream().map(board -> board.getDTO()).collect(Collectors.toList());
       int count = boardMapper.getCount(pageRequestDTO);

       PageResponseDTO <BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>builder()
               .dtoList(dtoList)
               .count(count)
               .build();

       return  pageResponseDTO;

    } // 무조건 1이랑 10으로 처리해야해서 세팅을 바꿔놓음

    @Override
    public BoardDTO read(Long bno) {
        Board board = boardMapper.select(bno);

        if (board != null) {
            return board.getDTO();
        }
        // return board != null? board.getDTO(): null; //if 문을 삼항연산자로 변경
        return null;
    }

    @Override
    public boolean remove(Long bno) {
        return boardMapper.delete(bno) > 0;
    }

    @Override
    public boolean modify(BoardDTO boardDTO) {

        boardMapper.deleteAttach(boardDTO.getBno());

        Board board = boardDTO.getDomain();

        Long bno = board.getBno();

        board.getAttachList().forEach(attach -> {
            attach.setBno(bno);
            boardMapper.insertAttach(attach);
        });

        return boardMapper.update(board) > 0; // 0보다 크면 true
        //도메인에 board 값이 있기 때문에 getDomain을 사용
    }

}
