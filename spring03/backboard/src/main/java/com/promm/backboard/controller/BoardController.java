package com.promm.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promm.backboard.entity.Board;
import com.promm.backboard.service.BoardService;

import lombok.RequiredArgsConstructor;



@RequestMapping("/board") //Restful URL 은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        List<Board> boards = boardService.findboardAll();
        model.addAttribute("boardList", boards);
        return "board/list";
    }
    
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable(name = "bno") Long bno,Model model) throws Exception{
        Board board = boardService.findboardById(bno);
        model.addAttribute("board",board);
        return "board/detail";
    }
    
}
