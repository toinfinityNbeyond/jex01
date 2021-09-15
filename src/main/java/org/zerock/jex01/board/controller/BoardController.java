package org.zerock.jex01.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.jex01.board.dto.BoardDTO;
import org.zerock.jex01.board.service.BoardService;
import org.zerock.jex01.board.service.TimeService;
import org.zerock.jex01.common.dto.PageResponseDTO;
import org.zerock.jex01.common.dto.PageMaker;
import org.zerock.jex01.common.dto.PageRequestDTO;

@Controller
@RequestMapping("/board/*") //getMapping + postMapping. 경로를 지정했을 때 다음과 같은 URL은 모두 Controller에서 처리.
@Log4j2
@RequiredArgsConstructor // @NonNull 이나 final이 붙은 변수에 대한 생성자를 만들어준다.
public class BoardController {

    private final TimeService timeService; //autowired대신 final-> 인터페이스만 바라보게

    private final BoardService boardService; //postMapping에서 사용

    @GetMapping("/time") //-> /board/time
    public void getTime(int num,Model model){
        log.info("==================controller getTime===================");
        log.info(num % 0);
        model.addAttribute("time", timeService.getNow());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public void registerGet(){ //항상 똑같은 페이지  -> void
        //자동으로 해당하는 jsp로 감
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes){ // 리다이렉트 하려고 String사용

        log.info("boardDTOM           " + boardDTO);

        //Long bno = 111L;
        //Long bno = boardService.register(boardDTO);
        Long bno = boardService.register(boardDTO);

        log.info("==================c              registerPost=========================");
        log.info(bno);
        redirectAttributes.addFlashAttribute("result",bno); //addFlashAttribute ->일회성으로 처리. 뒤로가기 누르면 주소값(bno)이 나와서.
        //알림창 나오게 값을 받는거라서 한 번만 사용-> 일회성

        return "redirect:/board/list"; // 리다이렉트 했을 때 새롭게 생성한 bno번호를보고 싶음 -> mybatis설정
    }

    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO, Model model){ //commonExceptionHandler가 예외처리해줘서 따로 신경 X , 페이지리퀘스트 자동으로 파라미터에 값 던진다.
        //jsp로 담아서 보내줘야 한다. -> model사용(담아서 사용할 떄는 model)
        log.info("c         getList........................................." + pageRequestDTO);//c : controller

        PageResponseDTO<BoardDTO> responseDTO = boardService.getDTOList(pageRequestDTO);

        //기본형의 경우 model 을 사용하지 않아도 자동으로 전달됨.
        model.addAttribute("dtoList", responseDTO.getDtoList()); //boardList.jsp를 찾아감

        int total = responseDTO.getCount();
        int page = pageRequestDTO.getPage();
        int size = pageRequestDTO.getSize();   // 세개 있으면 페이지 메이커를 만들 수 있다

        model.addAttribute("pageMaker", new PageMaker(page,size,total));   // 필요한거 전달


        //model.addAttribute("pageMaker", new PageMaker(pageRequestDTO.getPage();,size,total));  이런식으로 줄이는것도 가능
    }  // 페이지와 사이즈를 파라미터로 던진다.

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/read","/modify", "/read2"})
    public void read(Long bno, PageRequestDTO pageRequestDTO,Model model) {  //자동으로 모델에 전달. PageRequestDTO를 파라미터로 사용하지 않으면 개별 값을 다 파라미터로 선언해야함;;
        log.info("c   read" +  bno );
        log.info("c   read" + pageRequestDTO);


        model.addAttribute("boardDTO", boardService.read(bno)); // model 파라미터 자체가 다른 값 을 감싸서 전달. 파라미터 자체가 값을 가공해서 전달.


    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) { // RedirectAttributes -> 값을 자동으로 전달해주는 타입(역할) response.Sendredirect 의 역할  -> 리턴으로 감.
        log.info("c       remove:" + bno);

        if (boardService.remove(bno)) {
            log.info("remove success");
            redirectAttributes.addFlashAttribute("result", "success");  //모달
            //jsp가 없기 때문에 개발자 도구 콘솔창으로 확인시 키 값만 나온다. -> 확인을 위해서 사용.
        }
        return "redirect:/board/list";  //삭제를 하고 모달창이 떠야함.

    }

    @PreAuthorize("principal.mid == #boardDTO.writer")
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("---------------------");
        log.info("---------------------");
        log.info("---------------------");
        log.info(boardDTO);
        if (boardDTO.getFiles().size() > 0) {
            boardDTO.getFiles().forEach(dto -> log.info(dto));
        }
        log.info("---------------------");
        log.info("---------------------");
        log.info("---------------------");


        if (boardService.modify(boardDTO)) {
            redirectAttributes.addFlashAttribute("result", "modified"); //모달 창으로 보여주기 위해서.redirectAttributes 사용해서 일회성으로 함. 다시 돌아가면 모달창이 뜨면 안되기 때문에.

        }
        redirectAttributes.addAttribute("bno", boardDTO.getBno()); // 몇 번이 수정됐는지 보여 주기 위해bno 값 보냄. redirectAttributes 객체를 전달해주는 역할
        // 수정하고 목록으로 돌아갈 때 원래 보던 페이지로 돌아감
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        if(pageRequestDTO.getType() != null){
            redirectAttributes.addAttribute("type", pageRequestDTO.getType());
            redirectAttributes.addAttribute("keyword", pageRequestDTO.getKeyword());
        } //MODIFY 는 post의 방식의 검색처리

        return "redirect:/board/read"; // 수정 완료하면 read로 돌아감
    }

}
