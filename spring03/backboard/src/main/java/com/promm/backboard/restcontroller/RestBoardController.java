package com.promm.backboard.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.promm.backboard.dto.BoardDto;
import com.promm.backboard.dto.ReplayDto;
import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Category;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Slf4j
public class RestBoardController {
    
    private final BoardService boardService;
    private final CategoryService categoryService;

    @GetMapping("/list/{category}")
    @ResponseBody
    public List<BoardDto> boardList(
                            @PathVariable(name ="category") String category,
                            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
                            @RequestParam(name = "kw",defaultValue = "") String keyword) {
        Category cate = categoryService.findByTitle(category);
        Page<Board> pageBoard = boardService.findByAll(page,keyword,cate); // 검색 및 카테고리 추가

        List<BoardDto> boardDtoList = pageBoard.stream()
        .map(board -> {
            List<ReplayDto> replayDtoList = board.getReplayList().stream()
                    .map(replay -> 
                            ReplayDto.builder()
                            .rno(replay.getRno())
                            .content(replay.getContent())
                            .createDate(replay.getCreateDate())
                            .writer(replay.getWriter() != null ? replay.getWriter().getUsername() : null)
                            .build())
                    .collect(Collectors.toList());

            return BoardDto.builder()
                    .bno(board.getBno())
                    .category(board.getCategory().getTitle())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createDate(board.getCreateDate())
                    .writer(board.getWriter().getUsername())
                    .modifyDate(board.getModifyDate())
                    .hit(board.getHit())
                    .replayList(replayDtoList)
                    .build();
        })
        .collect(Collectors.toList());
        return boardDtoList;
    }
}
