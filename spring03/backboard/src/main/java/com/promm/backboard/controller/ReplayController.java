package com.promm.backboard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        replayService.replaySave(board, replayForm.getContent(),writer);
        return "redirect:/board/detail/{bno}";
    }
    
}
