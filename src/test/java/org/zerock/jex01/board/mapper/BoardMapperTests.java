package org.zerock.jex01.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.board.domain.Board;
import org.zerock.jex01.board.dto.BoardDTO;
import org.zerock.jex01.common.dto.PageResponseDTO;
import org.zerock.jex01.common.config.RootConfig;
import org.zerock.jex01.common.dto.PageRequestDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@ExtendWith(SpringExtension.class) // 해당코드가 스프링을 실행한다고 알려줌
@ContextConfiguration( classes = {BoardRootConfig.class, RootConfig.class}) //지정 된 클래스나 문자열을 이용해서 필요한 객체를 등록 (Spring의 Bean으로 등록된다.)
public class BoardMapperTests {

    @Autowired
    BoardMapper boardMapper;

    @Test
    public void testDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("user" + (i % 10))
                    .build();

            boardMapper.insert(board);

        });
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
//                        .type("TC")
//                        .keyword("AA")
                        .build();

        log.info(pageRequestDTO);

        //boardMapper.getList(pageRequestDTO).forEach(board -> log.info(board));

        List<BoardDTO> boardDTOList = boardMapper.getList(pageRequestDTO).stream().map(board -> board.getDTO()).collect(Collectors.toList());

        int count = boardMapper.getCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>builder()
                .dtoList(boardDTOList)
                .count(count)
                .build();

    }

    @Test
    public void  testSelect() {
        log.info(boardMapper.select(229L));
    }

    @Test
    public void testDelete() {
        long bno = 320L;

        log.info("delete...........");
        log.info(boardMapper.delete(bno));
    }

    @Test
    public void testUpdate() {

        Board board = Board.builder()
                .bno(319L)
                .title("수정된 제목")
                .content("수정된 내용")
                .build();

        log.info(boardMapper.update(board));

    }
}
