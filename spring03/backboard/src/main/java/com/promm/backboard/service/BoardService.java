package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promm.backboard.entity.Board;
import com.promm.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;

    //타이틀과 컨텐츠를 받아서 저장
    public Board boardSave(String title,String content){
        Board board = Board.builder().content(content).title(title).createDate(LocalDateTime.now()).build();
        return boardRepository.save(board);
    }


    //모든 컬럼 반환
    public List<Board> findboardAll(){
        return boardRepository.findAll();
    }

    //id로 찾기
    public Board findboardById(Long bno) throws Exception{
        if(boardRepository.findById(bno).isPresent()){
            return boardRepository.findById(bno).get();
        }else{
            throw new Exception("board not found");
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
    public void updateBoard(Long bno,String title, String content) throws Exception{
        if(boardRepository.findById(bno).isPresent()){
            Board board = boardRepository.findById(bno).get();
            board.setTitle(title);
            board.setContent(content);
            boardRepository.save(board);
        }else{
            throw new Exception("board not found");
        }
    }

    //업데이트 하기 타이틀만
    @Transactional
    public void updateBoardTitle(Long bno,String title) throws Exception{
        if(boardRepository.findById(bno).isPresent()){
            Board board = boardRepository.findById(bno).get();
            board.setTitle(title);
            boardRepository.save(board);
        }else{
            throw new Exception("board not found");
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
}
