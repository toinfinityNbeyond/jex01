package org.zerock.jex01.board.service;

import org.zerock.jex01.board.domain.Reply;
import org.zerock.jex01.board.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {


    int add(ReplyDTO replyDTO);
    List<ReplyDTO> getRepliesWithBno(long bno);

    int remove(long rno);

    int modify(ReplyDTO replyDTO);

    default Reply dtoToEntity (ReplyDTO dto) { //DTO -> NTT
        Reply reply = Reply.builder()
                            .rno(dto.getRno())
                            .bno(dto.getBno())
                            .reply(dto.getReply())
                            .replyer(dto.getReplyer())
                            .replyDate(dto.getReplyDate())
                            .modDate(dto.getModDate())
                            .build();

            return  reply;
    }
    default ReplyDTO entityToDTO(Reply reply) {  //원래 인터페이스는 가질 수 없다. 자바 8부터 가능



        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .bno(reply.getBno())
                .reply(reply.getReply())
                .replyer(reply.getReplyer())
                .replyDate(reply.getReplyDate())
                .modDate(reply.getModDate() )
                .build();

        return replyDTO;

    }
}
