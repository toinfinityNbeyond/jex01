package org.zerock.jex01.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.jex01.board.dto.ReplyDTO;

@Log4j2
@RestController    // 리턴하는 값이 모두다 json 처리가 된다. 기본자료형이나 문자열은 텍스트로 받아들인다. 객체타입은 전부 json 으로 반환
@RequestMapping("/replies")
public class ReplyController { // 댓글은 보드에 종속되어서 보드에 만듦.

    @GetMapping("")
    public String[] doA() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return new String[]{"AAA", "BBB", "CCC"};

    }


    @PostMapping("")
    public Long add(@RequestBody ReplyDTO replyDTO) {  // @RequestBody json으로 들어오는 데이터를 DTO로 바꿔준다.


        try {
            Thread.sleep(1000);
        } catch ( InterruptedException e) {
            e.printStackTrace();
        }

        log.info("========================");
        log.info(replyDTO);

        return 1L;
    }

    @DeleteMapping("/{rno}")  //리소스는 아이디를 가진다(쿠팡의 특정한 상품의 아이디)./{rno} 댓글 번호를 만든다. 리플라이즈의 100번을 delete 방식으로 호출한다는건  동사 delete를 선택해서 삭제하겠다는것
    //환경이 달라지면 작동을 안할 수도 있음(누군가 게시글을 작성하면 페이지주소가 달라져서 ). 주소가 변함이 없어야해서 URI의 PK값을 넣었다.

    //번호에 줬던애가 파라미터로 자동 수집. rno를 파라미터로 자동으로 수집
    // (Long rno) 이 값은 ("/{rno}")   이 값   // Long rno 이 값을 @PathVariable(name = "rno") 으로 이름을 준다(감싸주는 어노테이션) -> @DeleteMapping("/{rno}") 여기서 사용
    public String remove(@PathVariable(name = "rno") Long rno) {  //json으로 받을건지 파라미터를 받을건지 문제가 생김..(심각)

        log.info("----------reply remove-------");

        log.info("rno: " + rno);
        return "success";
    }

    @PutMapping("/{rno}")
    public String modify(@PathVariable(name = "rno") Long rno,
                         @RequestBody ReplyDTO replyDTO) {  // @RequestBody json으로 들어오는 데이터를 DTO로 바꿔준다.

        log.info("-------------reply modify----" + rno);
        log.info(replyDTO);


        return "success";
    }

}
