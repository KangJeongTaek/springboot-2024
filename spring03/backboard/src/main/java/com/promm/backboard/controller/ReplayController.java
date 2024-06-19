package com.promm.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.service.BoardService;
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


    @Transactional
    @PostMapping("/create/{bno}")
    public String replaysave(@PathVariable(name="bno") Long bno,
                                @Valid ReplayForm replayForm,BindingResult bindingResult,Model model) throws Exception{
        Board board = boardService.findboardById(bno);
        if(bindingResult.hasErrors()){
            model.addAttribute("board", board);
            List<Replay> replayList = replayService.findByBoardBno(bno);
            model.addAttribute("replayList", replayList);
            return "board/detail";
        }
        replayService.replaySave(board, replayForm.getContent());
        return "redirect:/board/detail/{bno}";
    }
    
}
