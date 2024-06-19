package com.promm.backboard.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.promm.backboard.entity.Board;
import com.promm.backboard.service.BoardService;

@DataJpaTest
class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private BoardService boardService;

    @Test
    void testInsertBoard(){
        Board board = Board.builder()
        .title("첫 번째 테스트입니다.")
        .content("아이웨옹ㅇ")
        .createDate(LocalDateTime.now())
        .build();
        boardRepository.save(board);
    }

    @Test
    void testInsertBoard2(){
        Board board1 = new Board();
        board1.setTitle("두 번째 테스트입니다.");
        board1.setContent("아이웽오");
        board1.setCreateDate(LocalDateTime.now());
        boardRepository.save(board1);
    }

    @Test
    void testSelectBoard(){
        List<Board> boardAll = boardRepository.findAll(); //select * from board
        assertEquals(0, boardAll.size());
    }

    @Test
    void testUpdateBoard(){
        Optional<Board> bd =boardRepository.findById(1L);
        assertFalse(bd.isPresent()); // 현재 db에 값이 없기 때문에 밑에 테스트를 실행 할 수 없다.
        // Board ubd = bd.get();
        // ubd.setContent("새로운 컨텐츠로 수정합니다.");
        // boardRepository.save(ubd); 
        //save() id가 없으면 INSERT, 있으면 UPDATE 쿼리 자동실행
    }

    @Test
    void testDeleteBoard(){
        // Optional<Board> bd = boardRepository.findById(1L);
        // Board ubd = bd.get();
        // boardRepository.delete(ubd);
        // boardRepository.deleteById(1L);
    }

    //페이지네이션 테스트
    // @Test
    // void testThreeHundredBoard(){
    //     for(int i =0 ; i<=300 ;i ++){
    //         boardService.boardSave(String.format("test data - [%03d]",i+1),"test용") ;
    //     }
    // }
}
