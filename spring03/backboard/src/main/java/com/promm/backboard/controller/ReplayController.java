package com.promm.backboard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Member;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.MemberService;
import com.promm.backboard.service.ReplayService;
import com.promm.backboard.validation.ReplayForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@Controller
@RequestMapping("/replay")
@RequiredArgsConstructor
public class ReplayController {
    
    private final ReplayService replayService;
    private final BoardService boardService;
    private final MemberService memberService;

    // Principal 객체 추가하면 로그인한 사용자명(Member객체)을 알 수 있음
    @Transactional
    @PostMapping("/create/{bno}")
    @PreAuthorize("isAuthenticated()")
    public String replaysave(@PathVariable(name="bno") Long bno,
                                @Valid ReplayForm replayForm,
                                BindingResult bindingResult,
                                Model model,
                                Principal principal) throws Exception{
        Board board = boardService.findboardById(bno);
        Member writer = memberService.membreFind(principal.getName()); //현재 로그인 중인 사용자의 ID를 가져온다.
        if(bindingResult.hasErrors()){
            model.addAttribute("board", board);
            List<Replay> replayList = replayService.findByBoardBno(bno);
            model.addAttribute("replayList", replayList);
            return "board/detail";
        }
        Replay replay = replayService.replaySave(board, replayForm.getContent(),writer);
        return "redirect:/board/detail/{bno}" + "#replay_" + replay.getRno();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{rno}")
    public String modify(ReplayForm replayForm,@PathVariable("rno") Long rno,Principal principal){
        Replay replay = replayService.getReplay(rno);
        if(!replay.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다.");
        }
        replayForm.setContent(replay.getContent());
        return "replay/modify";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{rno}")
    public String modify(@Valid ReplayForm replayForm,@PathVariable("rno") Long rno,BindingResult bindingResult,Principal principal) {
        if(bindingResult.hasErrors()){
            return "replay/modify"; // 현재 html에 그대로 머무르시오
        }
        Replay replay = replayService.getReplay(rno);
        if(!replay.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다.");
        }
        replayService.modReplay(replay, replayForm.getContent());
        // 수정이 완료되면 그 댓글로 보내기
        return "redirect:/board/detail/" + replay.getBoard().getBno() + "#replay_" + replay.getRno();
    }
    
    // 삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{rno}")
    public String delete(@PathVariable("rno") Long rno,Principal principal){
        Replay replay = replayService.getReplay(rno);
        if(!replay.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다.");
        }
        replayService.remReplay(replay);
        return "redirect:/board/detail/" + replay.getBoard().getBno();
    }
    
}
