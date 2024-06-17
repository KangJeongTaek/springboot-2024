package com.promm.backboard.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.promm.backboard.entity.Board;

@DataJpaTest
class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;

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
}
