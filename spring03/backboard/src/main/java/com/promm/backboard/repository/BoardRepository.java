package com.promm.backboard.repository;

import java.util.List;

//페이징을 위한 네임스페이스
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.promm.backboard.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{

    //메서드 명만 잘 만들면 작접 쿼리를 작성할 필요가 없다.
    Board findByBno(Long bno);

    Board findByTitle(String title);

    List<Board> findByTitleLike(String title);

    @SuppressWarnings("null") //경고 메시지를 무시할 수 있게 해주는 애노테이션
    Page<Board> findAll(Pageable pageable);

    Page<Board> findAll(Specification<Board> spec,Pageable pageable);
}
