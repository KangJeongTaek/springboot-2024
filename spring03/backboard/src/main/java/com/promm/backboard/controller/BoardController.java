package com.promm.backboard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Category;
import com.promm.backboard.entity.Member;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.CategoryService;
import com.promm.backboard.service.MemberService;
import com.promm.backboard.service.ReplayService;
import com.promm.backboard.validation.BoardForm;
import com.promm.backboard.validation.ReplayForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;






@RequestMapping("/board") //Restful URL 은 /board로 시작
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final ReplayService replayService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    // @GetMapping("/list")
    // public String boardList(Model model,@RequestParam(name = "page", required = false,defaultValue = "0")int page) {
    //     Page<Board> pageBoard = boardService.findByAll(page);
    //     model.addAttribute("paging", pageBoard);
    //     return "board/list";
    // }

    // 2024.06.24 list getMapping 새로 변경
    // @GetMapping("/list")
    public String boardList(Model model,@RequestParam(name = "page", required = false,defaultValue = "0")int page,
                            @RequestParam(name = "kw",defaultValue = "") String keyword) {
        Page<Board> pageBoard = boardService.findByAll(page,keyword); // 검색 추가
        model.addAttribute("paging", pageBoard);
        model.addAttribute("kw", keyword);
        return "board/list";
    }

    // 2024.06.25 마지막 카테고리까지 추가
    @GetMapping("/list/{category}")
    public String boardList(Model model,
                            @PathVariable(name ="category") String category,
                            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
                            @RequestParam(name = "kw",defaultValue = "") String keyword) {
        Category cate = categoryService.findByTitle(category);
        Page<Board> pageBoard = boardService.findByAll(page,keyword,cate); // 검색 및 카테고리 추가
        model.addAttribute("paging", pageBoard);
        model.addAttribute("kw", keyword);
        model.addAttribute("category", category);
        return "board/list";
    }
    
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable(name = "bno") Long bno,Model model,ReplayForm replayForm,HttpServletRequest httpServletRequest) {
        
        // Board board = boardService.findboardById(bno);
        Board board = boardService.hitBoard(bno); // 조회수 증가
        model.addAttribute("board",board);
        // 이전 페이지를 변수에 담기
        String prevUrl = httpServletRequest.getHeader("referer");
        log.info("★★★★★★★★★ 이전 페이지 = {}",prevUrl);
        List<Replay> replayList = replayService.findByBoardBno(bno);
        model.addAttribute("replayList", replayList);
        model.addAttribute("prevUrl", prevUrl);
        return "board/detail";
    }

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String create(BoardForm boardForm) {
        return "board/create";
    }

    @PostMapping("/create")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public String create(@Valid BoardForm boardForm, BindingResult bindingResult,Principal principal) {
        if(bindingResult.hasErrors()){
            return "board/create"; // 현재 html에 그대로 머무르시오
        }
        // board.setCreateDate(LocalDateTime.now());
        Member writer = memberService.membreFind(principal.getName()); 
        boardService.boardSave(boardForm.getTitle(),boardForm.getContent(),writer);
        return "redirect:/board/list";
    }

    @GetMapping("/create/{category}")
    @PreAuthorize("isAuthenticated()")
    public String create(@PathVariable("category")String category,BoardForm boardForm, Model model) {
        model.addAttribute("category", category);
        return "board/create";
    }

    @PostMapping("/create/{category}")
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public String create(@Valid BoardForm boardForm,
                        @PathVariable("category") String category,
                        BindingResult bindingResult,Principal principal,
                        Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "board/create"; // 현재 html에 그대로 머무르시오
        }
        // board.setCreateDate(LocalDateTime.now());
        Member writer = memberService.membreFind(principal.getName());
        Category cate = categoryService.findByTitle(category);
        boardService.boardSave(boardForm.getTitle(),boardForm.getContent(),writer,cate);
        return "redirect:/board/list/{category}";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{bno}")
    public String modify(@PathVariable(name = "bno") Long bno, BoardForm boardForm, Principal principal) {
        Board board = boardService.findboardById(bno);
        if(!board.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }

        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        return "board/create"; //게시글 등록 페이지를 재활용할 것임
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{bno}")
    public String modify(@PathVariable(name="bno") Long bno, @Valid BoardForm boardForm,BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "board/create"; // 현재 html에 그대로 머무르시오
        }
        Board board = boardService.findboardById(bno);
        if(!board.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다.");
        }
        boardService.modBoard(board, boardForm.getTitle(), boardForm.getContent());
    

        return "redirect:/board/detail/{bno}";
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{bno}")
    public String delete(@PathVariable("bno") Long bno,Principal principal) {
        Board board = boardService.findboardById(bno);
        if(!board.getWriter().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다.");
        }
        boardService.remBoard(board);
        return "redirect:/";
    }
    

    
    
    
    
}
