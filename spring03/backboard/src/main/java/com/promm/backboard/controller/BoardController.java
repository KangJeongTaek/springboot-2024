package com.promm.backboard.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.ReplayService;
import com.promm.backboard.validation.BoardForm;
import com.promm.backboard.validation.ReplayForm;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;





@RequestMapping("/board") //Restful URL 은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplayService replayService;

    @GetMapping("/list")
    public String boardList(Model model,@RequestParam(name = "page", required = false,defaultValue = "0")int page) {
        Page<Board> pageBoard = boardService.findByAll(page);
        model.addAttribute("paging", pageBoard);
        return "board/list";
    }
    
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable(name = "bno") Long bno,Model model,ReplayForm replayForm) throws Exception{
        
        Board board = boardService.findboardById(bno);
        model.addAttribute("board",board);

        List<Replay> replayList = replayService.findByBoardBno(bno);
        model.addAttribute("replayList", replayList);
        return "board/detail";
    }

    @GetMapping("/create")
    public String create(BoardForm boardForm) {
        return "board/create";
    }

    @PostMapping("/create")
    @Transactional
    public String create(@Valid BoardForm boardForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "board/create"; // 현재 html에 그대로 머무르시오
        }
        // board.setCreateDate(LocalDateTime.now());
        boardService.boardSave(boardForm.getTitle(),boardForm.getContent());
        return "redirect:/board/list";
    }
    
    
    
}
