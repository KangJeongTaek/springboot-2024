package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promm.backboard.common.NotFoundException;
import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Member;
import com.promm.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;

    //타이틀과 컨텐츠를 받아서 저장 24.06.18 boardSave 작성()
    // 24.06.21 Member 추가
    public Board boardSave(String title,String content,Member writer){
        Board board = Board.builder()
        .content(content).title(title)
        .createDate(LocalDateTime.now())
        .writer(writer).build();

        return boardRepository.save(board);
    }
    //객체로 저장
    public Board boardSave(Board board){
        return boardRepository.save(board);
    }


    //모든 컬럼 반환
    public List<Board> findboardAll(){
        return boardRepository.findAll();
    }

    //id로 찾기
    public Board findboardById(Long bno) {
        if(boardRepository.findById(bno).isPresent()){
            return boardRepository.findById(bno).get();
        }else{
            throw new NotFoundException("board not found");
        }
    }

    //board 객체를 통해 삭제하기
    @Transactional
    public void boardDelete(Board board){
        boardRepository.delete(board);
    }

    //id로 삭제하기
    @Transactional
    public void boardDeleteById(Long bno){
        boardRepository.deleteById(bno);
    }

    //업데이트 하기 타이틀과 컨텐츠 바꾸기
    @Transactional
    public void updateBoard(Long bno,String title, String content){
        if(boardRepository.findById(bno).isPresent()){
            Board board = boardRepository.findById(bno).get();
            board.setTitle(title);
            board.setContent(content);
            boardRepository.save(board);
        }else{
            throw new NotFoundException("board not found");
        }
    }

    //업데이트 하기 타이틀만
    @Transactional
    public void updateBoardTitle(Long bno,String title) {
        if(boardRepository.findById(bno).isPresent()){
            Board board = boardRepository.findById(bno).get();
            board.setTitle(title);
            boardRepository.save(board);
        }else{
            throw new NotFoundException("board not found");
        }
    }

    //업데이트 하기 컨텐츠만
    @Transactional
    public void updateBoardContent(Long bno,String content) throws Exception{
        if(boardRepository.findById(bno).isPresent()){
            Board board = boardRepository.findById(bno).get();
            board.setContent(content);
            boardRepository.save(board);
        }else{
            throw new Exception("board not found");
        }
    }

    //페이지 객체 반환하기
    public Page<Board> findByAll(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return boardRepository.findAll(pageable);
    }

}
