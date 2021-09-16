package org.zerock.jex01.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.jex01.board.dto.ReplyDTO;
import org.zerock.jex01.board.mapper.BoardMapper;
import org.zerock.jex01.board.mapper.ReplyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final ReplyMapper replyMapper;
    private final BoardMapper boardMapper;

    public int add(ReplyDTO replyDTO){
        int count = replyMapper.insert(dtoToEntity(replyDTO));
        boardMapper.updateReplyCnt(replyDTO.getBno(),1);
        return count;

    }


    @Override
    public List<ReplyDTO> getRepliesWithBno(long bno) {
        return replyMapper.getListWithBoard(bno).stream()
                .map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public int remove(long rno) {

        return replyMapper.delete(rno);
    }

    @Override
    public int modify(ReplyDTO replyDTO) {
        return replyMapper.update(dtoToEntity(replyDTO)); //replyDTO 를 NTT
    }


}
