package com.promm.backboard.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.promm.backboard.dto.BoardDto;
import com.promm.backboard.dto.Header;
import com.promm.backboard.dto.PagingDto;
import com.promm.backboard.dto.ReplayDto;
import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Category;
import com.promm.backboard.service.BoardService;
import com.promm.backboard.service.CategoryService;
import com.promm.backboard.validation.ReplayForm;

import jakarta.servlet.http.HttpServletRequest;
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
        public Header<List<BoardDto>> boardList(
                        @PathVariable(name = "category") String category,
                        @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(name = "kw", defaultValue = "") String keyword) {
                Category cate = categoryService.findByTitle(category);
                Page<Board> pageBoard = boardService.findByAll(page, keyword, cate); // 검색 및 카테고리 추가
                PagingDto pagingDto = new PagingDto(pageBoard.getTotalElements(),pageBoard.getNumber() +1, 10,10);

                long initialCurNum = pageBoard.getTotalElements() - (pageBoard.getNumber() * 10);
                AtomicLong curNum = new AtomicLong(initialCurNum);
                List<BoardDto> boardDtoList = pageBoard.stream()
                                .map(board -> {
                                        List<ReplayDto> replayDtoList = board.getReplayList().stream()
                                                        .map(replay -> ReplayDto.builder()
                                                                        .rno(replay.getRno())
                                                                        .content(replay.getContent())
                                                                        .createDate(replay.getCreateDate())
                                                                        .writer(replay.getWriter() != null? replay.getWriter().getUsername(): "")
                                                                        .build())
                                                        .collect(Collectors.toList());
                                        
                                        return BoardDto.builder()
                                                        .bno(board.getBno())
                                                        .category(board.getCategory().getTitle())
                                                        .title(board.getTitle())
                                                        .content(board.getContent())
                                                        .createDate(board.getCreateDate())
                                                        .writer(board.getWriter() !=null ? board.getWriter().getUsername() : "")
                                                        .modifyDate(board.getModifyDate())
                                                        .hit(board.getHit())
                                                        .replayList(replayDtoList)
                                                        //게시글 번호를 추가
                                                        .num(curNum.getAndDecrement())
                                                        .build();
                                })
                                .collect(Collectors.toList());

                // Header<>에 담기
                Header<List<BoardDto>> result = Header.OK(boardDtoList,pagingDto);
                return result;
        }



        @GetMapping("/detail/{bno}")
        @ResponseBody
        public BoardDto detail(@PathVariable(name = "bno") Long bno,Model model,ReplayForm replayForm,HttpServletRequest httpServletRequest) {
        
                // Board board = boardService.findboardById(bno);
                Board _board = boardService.hitBoard(bno); // 조회수 증가
                List<ReplayDto> replayList = new ArrayList<>();
                

                BoardDto board = BoardDto.builder().bno(_board.getBno())
                                                        .category(_board.getCategory().getTitle())
                                                        .content(_board.getContent())
                                                        .createDate(_board.getCreateDate())
                                                        .modifyDate(_board.getModifyDate())
                                                        .writer(_board.getWriter() != null ? _board.getWriter().getUsername() : "")
                                                        .title(_board.getTitle())
                                                        .build();

                _board.getReplayList().forEach(rpy -> replayList.add(ReplayDto.builder().content(rpy.getContent())
                                                                                        .createDate(rpy.getCreateDate())
                                                                                        .modifyDate(rpy.getModifyDate())
                                                                                        .rno(rpy.getRno())
                                                                                        .writer(rpy.getWriter() !=null ? rpy.getWriter().getUsername() : "")
                                                                                        .build()));
                board.setReplayList(replayList);
                
                

                
                // model.addAttribute("board",board);
                // 이전 페이지를 변수에 담기
                // String prevUrl = httpServletRequest.getHeader("referer");
                
                // List<Replay> replayList = replayService.findByBoardBno(bno);
                // model.addAttribute("replayList", replayList);
                // model.addAttribute("prevUrl", prevUrl);
                return board;
    }
}
