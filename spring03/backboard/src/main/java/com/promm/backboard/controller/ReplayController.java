package com.promm.backboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.promm.backboard.entity.Board;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.ReplayService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/replay")
@RequiredArgsConstructor
public class ReplayController {
    
    private final ReplayService replayService;
    private final BoardService boardService;


    @Transactional
    @PostMapping("/create/{bno}")
    public String replaysave(@PathVariable(name="bno") Long bno,
                                @RequestParam(name = "content") String content) throws Exception{
        Board board = boardService.findboardById(bno);
        replayService.replaySave(board, content);
        return "redirect:/board/detail/{bno}";
    }
    
}
