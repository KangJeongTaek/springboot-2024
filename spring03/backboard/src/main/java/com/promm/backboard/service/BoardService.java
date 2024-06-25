package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification; //복합쿼리 생성용
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promm.backboard.common.NotFoundException;
import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Category;
import com.promm.backboard.entity.Member;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.repository.BoardRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    // 24.06.25 category 추가
    public Board boardSave(String title, String content,Member writer,Category category){
        Board board = Board.builder()
        .content(content)
        .title(title)
        .createDate(LocalDateTime.now())
        .writer(writer)
        .category(category)
        .build();
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
    // 선생님과 메서드 같이 만들기 2024.06.24
    @Transactional
    public void remBoard(Board board){
        boardRepository.delete(board);
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

    // 선생님과 메서드 같이 만들기
    // 2024 06 24 추가
    @Transactional
    public void modBoard(Board board, String title, String content){
        board.setTitle(title);
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now());
        boardRepository.save(board); //PK가 없다면 insert 있다면 update
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

    //24.06.24 검색 메서드 추가
    public Page<Board> findByAll(int page,String keyword){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        // Specification<Board> spec = searchBoard(keyword);
        // return boardRepository.findAll(spec,pageable);
        return boardRepository.findAllByKeyword(keyword, pageable);
    }
      //24.06.25 카테고리 추가
      public Page<Board> findByAll(int page,String keyword,Category category){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Board> spec = searchBoard(keyword,category.getId());
        return boardRepository.findAll(spec,pageable);
    }

    public Specification<Board> searchBoard(String keyword){
        return new Specification<Board>() {
            private static final long serialVersionUID = 1L; // 필요한 값이라 추가

            @Override
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // query를 JPA로 작성
                query.distinct(true);
                Join<Board,Replay> r = b.join("replayList",JoinType.LEFT);
                return cb.or(cb.like(b.get("title"), "%" + keyword + "%"), // 게시글의 제목에서 검색
                            cb.like(b.get("content"),"%"+keyword +"%"), //게시글 내용에서 검색
                            cb.like(r.get("content"),"%" + keyword + "%")); //댓글 내용에서 검색
            }
        };
    }

    public Specification<Board> searchBoard(String keyword,Integer cateId){
        return new Specification<Board>() {
            private static final long serialVersionUID = 1L; // 필요한 값이라 추가

            @Override
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // query를 JPA로 작성
                query.distinct(true);
                Join<Board,Replay> r = b.join("replayList",JoinType.LEFT);
                return 
                cb.and(cb.equal(b.get("category").get("id"), cateId),
                cb.or(cb.like(b.get("title"), "%" + keyword + "%"), // 게시글의 제목에서 검색
                            cb.like(b.get("content"),"%"+keyword +"%"), //게시글 내용에서 검색
                            cb.like(r.get("content"),"%" + keyword + "%") //댓글 내용에서 검색
                    )); 
            }
        };
    }
    
}
